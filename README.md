﻿# Моделирование и анализ параллельных алгоритмов (2020)

# ЛР1. Синхронизация параллельных процессов. Производитель-потребитель
Изучение влияния синхронизации на производительность параллельных программ

Разработать параллельную программу, выполняющую копирование данных из одного массива (A) в другой массив (B) с использованием промежуточного буфера. 

Программа должна состоять из двух потоков:
* первый поток – производитель – содержит исходный массив А и копирует данные из него в буфер по одному элементу;
* второй поток содержит массив В и производит чтение данных из буфера по одному элементу и запись их в массив В.

Запись в буфер возможна при наличии в нем хотя бы одного свободного элемента.
Чтение из буфера возможно при наличии в нем хотя бы одного несчитанного элемента. 

С буфером связаны два счетчика: 
* первый счётчик содержит индекс первого доступного элемента для чтения (начало данных), 
* второй счётчик содержит индекс первого доступного для записи элемента. 

Если счётчики равны, буфер пуст. Буфер циклический, то есть второй счётчик может быть меньше первого. Если второй счетчик меньше первого на 1, то буфер заполнен.

Альтернативным подходом может служить учёт только занятых элементов буфера. В этом случае первый счётчик содержит индекс первого занятого элемента, второй счётчик содержит индекс последнего занятого элемента.

# ЛР2. Синхронизация параллельных процессов. Сравнение файлов
Изучение влияния синхронизации на производительность параллельных программ

Разработать параллельную программу, выполняющую сравнение двух файлов (по аналогии с утилитой Linux diff). Программа должна построчно сравнивать два текстовых файла и выводить на стандартный вывод номера и значения отличающихся строк. 

Если один из файлов длиннее другого, то программа должна вывести каждую дополнительную строку более длинного файла. 

Программа должна состоять из трёх потоков: 
* по одному потоку на построчное чтение каждого файла 
* и один поток на сравнение двух считанных строк. 

Строки из разных файлов могут читаться одновременно, но сравнение происходит, когда считаны обе строки с одинаковыми номерами. Пока процесс, сравнивающий строки, не выполнит сравнение, процессы-читатели ждут и не читают очередные строки.

# ЛР3. Модели взаимодействия параллельных процессов. Нахождение простых чисел
Изучение различных парадигм параллельного программирования и моделей взаимодействия параллельных процессов

С использованием одной из моделей взаимодействия параллельных процессов разработать параллельную программу нахождения всех простых чисел в диапазоне от 1 до N. Целое число X>1 называется простым, если оно делится без остатка только на X и на 1. Любое целое число Y>1 можно представить в виде произведения простых чисел.

Программа должна работать с любым количеством потоков, задаваемым пользователем. Программа должна реализовывать следующий алгоритм:
1) диапазон [1, N] разбивается на P частей, где P — количество потоков или процессов параллельной программы;
2) каждый поток находит простые числа в своём диапазоне, последовательно проверяя все числа внутри этого диапазона;
3) все потоки используют общий буфер (одномерный массив) для хранения найденных простых чисел;
4) каждый поток при нахождении очередного простого числа добавляет его в буфер;
5) определение того, является очередное проверяемое число простым или нет, выполняется его делением на уже найденные простые числа, находящиеся в общем буфере. 

При этом возникает задача синхронизации: при анализе очередного числа Y поток должен убедиться, что буфер содержит все числа, меньшие Y, которые могут быть его делителями. 
Поскольку некоторые из этих чисел добавляются в буфер другими потоками, текущий поток должен приостановиться и подождать, когда остальные потоки добавят все необходимые ему числа.


