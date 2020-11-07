using System;
using System.Diagnostics;
using System.Threading;

namespace Lab1
{
    public static class Program
    {
        private static int[] arrayA;
        private static int[] arrayB;
        private static int[] bufferArray;

        private static int writingIndex = 0;
        private static int readingIndex = 0;

        private static readonly object monitor = new object();

        public static void Main()
        {
            PrintAbout();
            PrintCreateArrays();

            PrintSequentialRun();
            PrintParallelRun();
       
            Console.WriteLine("Нажмите Enter, чтобы закрыть окно программы...");
            Console.ReadLine();
        }

        private static void PrintAbout()
        {
            Console.WriteLine("Лабораторная работа №1\n" +
                "Изучение влияния синхронизации на производительность параллельных программ");
            Console.WriteLine("Выполнил студент группы 6138 А.П. Кузнецов\n");
        }

        private static void PrintCreateArrays()
        {
            Console.Write("Введите размер массивов A и B ... ");
            int arraySize = int.Parse(Console.ReadLine());

            Console.Write("Введите размер буфера ........... ");
            int bufferSize = int.Parse(Console.ReadLine());
            Console.WriteLine();

            CreateArrays(arraySize, bufferSize);
        }

        private static void CreateArrays(int arraySize, int bufferSize)
        {
            arrayA = new int[arraySize];
            arrayB = new int[arrayA.Length];
            bufferArray = new int[bufferSize];

            Random random = new Random();
            for (int i = 0; i < arraySize; i++)
            {
                arrayA[i] = random.Next(1001);
            }

            writingIndex = 0;
            readingIndex = 0;
        }

        private static void PrintSequentialRun()
        {
            Stopwatch stopwatch = new Stopwatch();     
            stopwatch.Start();

            SequentialRun();

            stopwatch.Stop();
            
            Console.WriteLine("Копирование " + (AreThisArraysEqual() ? "УСПЕШНО" : "НЕ УСПЕШНО"));
            Console.WriteLine("Время последовательного копирования = " + stopwatch.ElapsedMilliseconds + " миллисекунд\n");
        }

        // Последовательное копирование
        private static void SequentialRun()
        {
            while (readingIndex < arrayB.Length)
            {
                do
                {
                    bufferArray[writingIndex % bufferArray.Length] = arrayA[writingIndex];
                    writingIndex++;
                } while ((writingIndex + 1) % bufferArray.Length != 0 && (writingIndex < arrayA.Length));

                do
                {
                    arrayB[readingIndex] = bufferArray[readingIndex % bufferArray.Length];
                    readingIndex++;
                } while (readingIndex < writingIndex && readingIndex < arrayB.Length);
            }
        }

        private static bool AreThisArraysEqual()
        {
            for (int i = 0; i < arrayB.Length; i++)
            {
                if (arrayA[i] != arrayB[i])
                    return false;
            }

            return true;
        }

        private static void PrintParallelRun()
        {
            writingIndex = 0;
            readingIndex = 0;

            Thread producer = new Thread(new ThreadStart(ProducerRun));
            Thread consumer = new Thread(new ThreadStart(ConsumerRun));

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();

            producer.Start();
            consumer.Start();
            consumer.Join();

            stopwatch.Stop();

            Console.WriteLine("Копирование " + (AreThisArraysEqual() ? "УСПЕШНО" : "НЕ УСПЕШНО"));
            Console.WriteLine("Время параллельного     копирования = " + stopwatch.ElapsedMilliseconds + " миллисекунд\n");
        }

        // Производитель-метод (при создании потока передаётся в его конструктор). Переносит данные из начального массива в буфер
        private static void ProducerRun()
        {
            while (writingIndex < arrayA.Length)
            {
                if (writingIndex - readingIndex + 1 < bufferArray.Length)
                {
                    lock (monitor) // синхронизированный блок кода
                    {
                        if (writingIndex < arrayA.Length)
                            bufferArray[writingIndex % bufferArray.Length] = arrayA[writingIndex];
                        
                        writingIndex++;
                    }
                }
                else
                {
                    Thread.Sleep(1);
                }                 
            }
        }

        // Потребитель (при создании потока передаётся в его конструктор). Переносит данные из буфера в результирующий массив
        private static void ConsumerRun()
        {
            while (readingIndex < arrayB.Length)
            {
                if (writingIndex > 0 && readingIndex < writingIndex)
                {
                    lock (monitor)
                    {
                        if (readingIndex < arrayB.Length)
                            arrayB[readingIndex] = bufferArray[readingIndex % bufferArray.Length];
                        
                        readingIndex++;
                    }
                }
                else
                {
                    Thread.Sleep(1);
                }
            }
        }
    }
}
