1. Stream

Stream — это объект для универсальной работы с данными. Мы указываем, какие операции хотим провести, при этом не заботясь о деталях реализации. Например, взять элементы из списка сотрудников, выбрать тех, кто младше 40 лет, отсортировать по фамилии и поместить в новый список. Или чуть сложнее, прочитать все json-файлы, находящиеся в папке books, десериализировать в список объектов книг, обработать элементы всех этих списков, а затем сгруппировать книги по автору.

Данные могут быть получены из источников, коими являются коллекции или методы, поставляющие данные. Например, список файлов, массив строк, метод range() для числовых промежутков и т.д. То есть, стрим использует существующие коллекции для получения новых элементов, это ни в коем случае не новая структура данных.
К данным затем применяются операторы. Например, взять лишь некоторые элементы (filter), преобразовать каждый элемент (map), посчитать сумму элементов или объединить всё в один объект (reduce).
Операторы можно разделить на две группы:
- Промежуточные (intermediate) — обрабатывают поступающие элементы и возвращают стрим. Промежуточных операторов в цепочке обработки элементов может быть много.
- Терминальные (terminal) — обрабатывают элементы и завершают работу стрима, так что терминальный оператор в цепочке может быть только один.

2. Получение объекта Stream

Пока что хватит теории. Пришло время посмотреть, как создать или получить объект java.util.stream.Stream.
- Пустой стрим: Stream.empty() // Stream<String>
- Стрим из List: list.stream() // Stream<String>
- Стрим из Map: map.entrySet().stream() // Stream<Map.Entry<String, String>>
- Стрим из массива: Arrays.stream(array) // Stream<String>
- Стрим из указанных элементов: Stream.of("a", "b", "c") // Stream<String>

А вот и пример:

    public static void main(String[] args) {
        List<String> list = Arrays.stream(args)
            .filter(s -> s.length() <= 2)
            .collect(Collectors.toList());
    }
В данном примере источником служит метод Arrays.stream, который из массива args делает стрим. Промежуточный оператор filter отбирает только те строки, длина которых не превышает два. Терминальный оператор collect собирает полученные элементы в новый список.

И ещё один пример:

    IntStream.of(120, 410, 85, 32, 314, 12)
        .filter(x -> x < 300)
        .map(x -> x + 11)
        .limit(3)
        .forEach(System.out::print)


Здесь уже три промежуточных оператора:
- filter — отбирает элементы, значение которых меньше 300,
- map — прибавляет 11 к каждому числу,
- limit — ограничивает количество элементов до 3.

Терминальный оператор forEach применяет функцию print к каждому приходящему элементу.
На ранних версиях Java этот пример выглядел бы так:
    int[] arr = {120, 410, 85, 32, 314, 12};
    int count = 0;
    for (int x : arr) {
        if (x >= 300) continue;
        x += 11;
        count++;
        if (count > 3) break;
        System.out.print(x);
    }

С увеличением числа операторов код в ранних версиях усложнялся бы на порядок, не говоря уже о том, что разбить вычисления на несколько потоков при таком подходе было бы крайне непросто.


3. Как работает стрим

У стримов есть некоторые особенности. Во-первых, обработка не начнётся до тех пор, пока не будет вызван терминальный оператор. list.stream().filter(x -> x > 100); не возьмёт ни единого элемента из списка. Во-вторых, стрим после обработки нельзя переиспользовать.


    Stream<String> stream = list.stream();
    stream.forEach(System.out::println);
    stream.filter(s -> s.contains("Stream API"));
    stream.forEach(System.out::println);

Код на второй строке выполнится, а вот на третьей выбросит исключение java.lang.IllegalStateException: stream has already been operated upon or closed.

Исходя из первой особенности, делаем вывод, что обработка происходит от терминального оператора к источнику. Это действительно так и это удобно. Мы можем в качестве источника использовать генерируемую бесконечную последовательность, скажем, факториала или чисел Фибоначчи, но обрабатывать лишь некоторую её часть.
Пока мы не присоединили терминальный оператор, доступа к источнику не проводилось. Как только появился терминальный оператор forEach, он стал запрашивать элементы у стоящего перед ним оператора limit. Тот в свою очередь обращается к map, map к filter, а filter уже обращается к источнику. Затем элементы поступают в прямом порядке: источник, filter, map, limit и forEach.
Пока какой-либо из операторов не обработает элемент должным образом, новые запрошены не будут.
Как только через оператор limit прошло 3 элемента, он переходит в закрытое состояние и больше не будет запрашивать элементы у map. forEach запрашивает очередной элемент, но limit сообщает, что больше не может поставить элементов, поэтому forEach делает вывод, что элементы закончились и прекращает работу.

Такой подход зовётся pull iteration, то есть элементы запрашиваются у источника по мере надобности. К слову, в RxJava реализован push iteration подход, то есть источник сам уведомляет, что появились элементы и их нужно обработать.
4. Параллельные стримы

Стримы бывают последовательными (sequential) и параллельными (parallel). Последовательные выполняются только в текущем потоке, а вот параллельные используют общий пул ForkJoinPool.commonPool(). При этом элементы разбиваются (если это возможно) на несколько групп и обрабатываются в каждом потоке отдельно. Затем на нужном этапе группы объединяются в одну для предоставления конечного результата.

Чтобы получить параллельный стрим, нужно либо вызвать метод parallelStream() вместо stream(), либо превратить обычный стрим в параллельный, вызвав промежуточный оператор parallel.
5. Стримы для примитивов

Кроме объектных стримов Stream<T>, существуют специальные стримы для примитивных типов:
- IntStream для int,
- LongStream для long,
- DoubleStream для double.

Для boolean, byte, short и char специальных стримов не придумали, но вместо них можно использовать IntStream, а затем приводить к нужному типу. Для float тоже придётся воспользоваться DoubleStream.

Примитивные стримы полезны, так как не нужно тратить время на боксинг/анбоксинг, к тому же у них есть ряд специальных операторов, упрощающих жизнь. Их мы рассмотрим очень скоро.


6. Операторы Stream API

Дальше приводятся операторы Stream API с описанием, демонстрацией и примерами. Можете использовать это как справочник.
empty()
Стрим, как и коллекция, может быть пустым, а значит всем последующем операторам нечего будет обрабатывать.

    Stream.empty()
        .forEach(System.out::println);
    // Вывода нет



of(T value)
of(T... values)
Стрим для одного или нескольких перечисленных элементов. Очень часто вижу, что используют такую конструкцию:

    Arrays.asList(1, 2, 3).stream()
        .forEach(System.out::println);

однако она излишня. Вот так проще:

    Stream.of(1, 2, 3)
        .forEach(System.out::println);



ofNullable(T t)
Появился в Java 9. Возвращает пустой стрим, если в качестве аргумента передан null, в противном случае, возвращает стрим из одного элемента.

    String str = Math.random() > 0.5 ? "I'm feeling lucky" : null;
    Stream.ofNullable(str)
        .forEach(System.out::println);



generate(Supplier s)
Возвращает стрим с бесконечной последовательностью элементов, генерируемых функцией Supplier s.

    Stream.generate(() -> 6)
        .limit(6)
        .forEach(System.out::println);
    // 6, 6, 6, 6, 6, 6

Поскольку стрим бесконечный, нужно его ограничивать или осторожно использовать, дабы не попасть в бесконечный цикл.


iterate(T seed, UnaryOperator f)
Возвращает бесконечный стрим с элементами, которые образуются в результате последовательного применения функции f к итерируемому значению. Первым элементом будет seed, затем f(seed), затем f(f(seed)) и так далее.


    Stream.iterate(2, x -> x + 6)
        .limit(6)
        .forEach(System.out::println);
    // 2, 8, 14, 20, 26, 32


    Stream.iterate(1, x -> x * 2)
        .limit(6)
        .forEach(System.out::println);
    // 1, 2, , , , 32



iterate(T seed, Predicate hasNext, UnaryOperator f)
Появился в Java 9. Всё то же самое, только добавляется ещё один аргумент hasNext: если он возвращает false, то стрим завершается. Это очень похоже на цикл for:

    for (i = seed; hasNext(i); i = f(i)) {
    }

Таким образом, с помощью iterate теперь можно создать конечный стрим.

    Stream.iterate(2, x -> x < 25, x -> x + 6)
        .forEach(System.out::println);
    // 2, 8, 14, 20


    Stream.iterate(4, x -> x < 100, x -> x * 4)
        .forEach(System.out::println);
    // , 16,



concat(Stream a, Stream b)
Объединяет два стрима так, что вначале идут элементы стрима A, а по его окончанию последуют элементы стрима B.


    Stream.concat(
            Stream.of(1, 2, 3),
            Stream.of(4, 5, 6))
        .forEach(System.out::println);
    // 1, 2, 3, 4, 5, 6


    Stream.concat(
            Stream.of(10),
            Stream.of(, ))
        .forEach(System.out::println);
    // 10, 4, 16



builder()
Создаёт мутабельный объект для добавления элементов в стрим без использования какого-либо контейнера для этого.


    Stream.Builder<Integer> streamBuider = Stream.<Integer>builder()
            .add(0)
            .add(1);
    for (int i = 2; i <= 8; i += 2) {
        streamBuider.accept(i);
    }
    streamBuider
        .add(9)
        .add(10)
        .build()
        .forEach(System.out::println);
    // 0, 1, 2, 4, 6, 8, 9, 10



IntStream.range(int startInclusive, int endExclusive)
LongStream.range(long startInclusive, long endExclusive)
Создаёт стрим из числового промежутка [start..end), то есть от start (включительно) по end.


    IntStream.range(0, 10)
        .forEach(System.out::println);
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
     
    LongStream.range(-10L, -5L)
        .forEach(System.out::println);
    // -10, -9, -8, -7, -6



IntStream.rangeClosed(int startInclusive, int endInclusive)
LongStream.rangeClosed(long startInclusive, long endInclusive)
Создаёт стрим из числового промежутка [start..end], то есть от start (включительно) по end (включительно).


    IntStream.rangeClosed(0, 5)
        .forEach(System.out::println);
    // 0, 1, 2, 3, 4, 5
     
    LongStream.range(-8L, -5L)
        .forEach(System.out::println);
    // -8, -7, -6, -5



6.2. Промежуточные операторы

filter(Predicate predicate)
Фильтрует стрим, принимая только те элементы, которые удовлетворяют заданному условию.


    Stream.of(1, 2, 3)
        .filter(x -> x == 10)
        .forEach(System.out::print);
    // Вывода нет, так как после фильтрации стрим станет пустым
     
    Stream.of(120, 410, 85, 32, 314, 12)
        .filter(x -> x > 100)
        .forEach(System.out::println);
    // 120, 410, 314


    IntStream.range(2, 9)
        .filter(x -> x % == 0)
        .forEach(System.out::println);
    // 3, 6



map(Function mapper)
Применяет функцию к каждому элементу и затем возвращает стрим, в котором элементами будут результаты функции. map можно применять для изменения типа элементов.

Stream.mapToDouble(ToDoubleFunction mapper)
Stream.mapToInt(ToIntFunction mapper)
Stream.mapToLong(ToLongFunction mapper)
IntStream.mapToObj(IntFunction mapper)
IntStream.mapToLong(IntToLongFunction mapper)
IntStream.mapToDouble(IntToDoubleFunction mapper)

Специальные операторы для преобразования объектного стрима в примитивный, примитивного в объектный, либо примитивного стрима одного типа в примитивный стрим другого.

    Stream.of("3", "4", "5")
        .map(Integer::parseInt)
        .map(x -> x + 10)
        .forEach(System.out::println);
    // 13, 14, 15
     
    Stream.of(120, 410, 85, 32, 314, 12)
        .map(x -> x + 11)
        .forEach(System.out::println);
    // 131, 421, 96, 43, 325, 23


    Stream.of("10", "11", "")
        .map(x -> Integer.parseInt(x, 16))
        .forEach(System.out::println);
    // , , 50



flatMap(Function<T, Stream<R>> mapper)
Один из самых интересных операторов. Работает как map, но с одним отличием — можно преобразовать один элемент в ноль, один или множество других.

flatMapToDouble(Function mapper)
flatMapToInt(Function mapper)
flatMapToLong(Function mapper)

Как и в случае с map, служат для преобразования в примитивный стрим.

Для того, чтобы один элемент преобразовать в ноль элементов, нужно вернуть null, либо пустой стрим. Чтобы преобразовать в один элемент, нужно вернуть стрим из одного элемента, например, через Stream.of(x). Для возвращения нескольких элементов, можно любыми способами создать стрим с этими элементами.



    Stream.of(2, 3, 0, 1, 3)
        .flatMap(x -> IntStream.range(0, x))
        .forEach(System.out::println);
    // 0, 1, 0, 1, 2, 0, 0, 1, 2


    Stream.of(1, 2, 3, 4, 5, 6)
        .flatMap(x -> {
             switch (x % ) {
                 case 0:
                     return Stream.of(x, x*x, x*x*);
                 case 1:
                     return Stream.of(x);
                 case 2:
                 default:
                     return Stream.empty();
             }
         })
        .forEach(System.out::println);
    // 1, 3, 9, 18, 4, 6, 36, 72



mapMulti(BiConsumer<T, Consumer<R>> mapper)
Появился в Java 16. Этот оператор похож на flatMap, но использует императивный подход при работе. Теперь вместе с элементом стрима приходит ещё и Consumer, в который можно передать одно или несколько значений, либо не передавать вовсе.

Вот как было с flatMap:


    Stream.of(1, 2, 3, 4, 5, 6)
        .flatMap(x -> {
             if (x % 2 == 0) {
                 return Stream.of(-x, x);
             }
             return Stream.empty();
         })
        .forEach(System.out::println);
    // -2, 2, -4, 4, -6, 6

А вот так можно переписать с использованием mapMulti:


    Stream.of(1, 2, 3, 4, 5, 6)
        .mapMulti((x, consumer) -> {
             if (x % 2 == 0) {
                 consumer.accept(-x);
                 consumer.accept(x);
             }
         })
        .forEach(System.out::println);
    // -2, 2, -4, 4, -6, 6


mapMultiToDouble(BiConsumer<T, DoubleConsumer> mapper)
mapMultiToInt(BiConsumer<T, IntConsumer> mapper)
mapMultiToLong(BiConsumer<T, LongConsumer> mapper)

Служат для преобразования в примитивный стрим.

У mapMulti есть несколько преимуществ перед flatMap. Во-первых, если приходится пропускать значения (как в примере выше, где пропускались нечётные элементы), то не будет затрат на создание пустого стрима. Во-вторых, consumer легко передать в другой метод, в котором можно проводить преобразования, включая рекурсивные.


    void processSerializable(Serializable ser, Consumer<String> consumer) {
        if (ser instanceof String str) {
            consumer.accept(str);
        } else if (ser instanceof List) {
            for (Serializable s : (List<Serializable>) ser) {
                processSerializable(s, consumer);
            }
        }
    }
     
    Serializable arr(Serializable... elements) {
        return Arrays.asList(elements);
    }
     
    Stream.of(arr("A", "B"), "C", "D", arr(arr("E"), "F"), "G")
        .mapMulti(this::processSerializable)
        .forEach(System.out::println);
    // A, B, C, D, E, F, G



limit(long maxSize)
Ограничивает стрим maxSize элементами.


    Stream.of(120, 410, 85, 32, 314, 12)
        .limit(4)
        .forEach(System.out::println);
    // 120, 410, 85, 32


    Stream.of(120, 410, 85, 32, 314, 12)
        .limit()
        .limit(5)
        .forEach(System.out::println);
    // 120, 410
     
    Stream.of(19)
        .limit()
        .forEach(System.out::println);
    // Вывода нет



skip(long n)
Пропускает n элементов стрима.


    Stream.of(5, 10)
        .skip(40)
        .forEach(System.out::println);
    // Вывода нет
     
    Stream.of(120, 410, 85, 32, 314, 12)
        .skip(2)
        .forEach(System.out::println);
    // 85, 32, 314, 12


    IntStream.range(0, 10)
        .limit(5)
        .skip(3)
        .forEach(System.out::println);
    // ,
     
    IntStream.range(0, 10)
        .skip(5)
        .limit(3)
        .skip(1)
        .forEach(System.out::println);
    // ,



sorted()
sorted(Comparator comparator)
Сортирует элементы стрима. Причём работает этот оператор очень хитро: если стрим уже помечен как отсортированный, то сортировка проводиться не будет, иначе соберёт все элементы, отсортирует их и вернёт новый стрим, помеченный как отсортированный. См. 9.1.


    IntStream.range(0, 100000000)
        .sorted()
        .limit(3)
        .forEach(System.out::println);
    // 0, 1, 2
     
    IntStream.concat(
            IntStream.range(0, 100000000),
            IntStream.of(-1, -2))
        .sorted()
        .limit(3)
        .forEach(System.out::println);
    // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     
    Stream.of(120, 410, 85, 32, 314, 12)
        .sorted()
        .forEach(System.out::println);
    // 12, 32, 85, 120, 314, 410


    Stream.of(120, 410, 85, 32, 314, 12)
        .sorted(Comparator.Order())
        .forEach(System.out::println);
    // 410, 314, 120, 85, 32, 12



distinct()
Убирает повторяющиеся элементы и возвращаем стрим с уникальными элементами. Как и в случае с sorted, смотрит, состоит ли уже стрим из уникальных элементов и если это не так, отбирает уникальные и помечает стрим как содержащий уникальные элементы.

    Stream.of(2, 1, 8, 1, 3, 2)
        .distinct()
        .forEach(System.out::println);
    // 2, 1, 8, 3


    IntStream.concat(
            IntStream.range(2, 5),
            IntStream.range(0, 4))
        .distinct()
        .forEach(System.out::println);
    // , , , ,



peek(Consumer action)
Выполняет действие над каждым элементом стрима и при этом возвращает стрим с элементами исходного стрима. Служит для того, чтобы передать элемент куда-нибудь, не разрывая при этом цепочку операторов (вы же помните, что forEach — терминальный оператор и после него стрим завершается?), либо для отладки.


    Stream.of(0, 3, 0, 0, 5)
        .peek(x -> System.out.format("before distinct: %d%n", x))
        .distinct()
        .peek(x -> System.out.format("after distinct: %d%n", x))
        .map(x -> x * x)
        .forEach(x -> System.out.format("after map: %d%n", x));
    // before distinct: 0
    // after distinct: 0
    // after map: 0
    // before distinct: 3
    // after distinct: 3
    // after map: 9
    // before distinct: 1
    // after distinct: 1
    // after map: 1
    // before distinct: 5
    // before distinct: 0
    // before distinct: 5
    // after distinct: 5
    // after map: 25
takeWhile(Predicate predicate)
Появился в Java 9. Возвращает элементы до тех пор, пока они удовлетворяют условию, то есть функция-предикат возвращает true. Это как limit, только не с числом, а с условием.

    Stream.of(1, 2, 3, 4, 2, 5)
        .takeWhile(x -> x < 3)
        .forEach(System.out::println);
    // 1, 2


    IntStream.range(2, 7)
        .takeWhile(x -> x != )
        .forEach(System.out::println);
    // 2, 3, 4



dropWhile(Predicate predicate)
Появился в Java 9. Пропускает элементы до тех пор, пока они удовлетворяют условию, затем возвращает оставшуюся часть стрима. Если предикат вернул для первого элемента false, то ни единого элемента не будет пропущено. Оператор подобен skip, только работает по условию.


    Stream.of(1, 2, 3, 4, 2, 5)
        .dropWhile(x -> x >= 3)
        .forEach(System.out::println);
    // 1, 2, 3, 4, 2, 5
     
    Stream.of(1, 2, 3, 4, 2, 5)
        .dropWhile(x -> x < 3)
        .forEach(System.out::println);
    // 3, 4, 2, 5


    IntStream.range(2, 7)
        .dropWhile(x -> x < )
        .forEach(System.out::println);
    // 5, 6
     
    IntStream.of(1, 3, 2, 0, 5, 4)
        .dropWhile(x -> x 2 == 1)
        .forEach(System.out::println);
    // 2, 0, 5, 6



boxed()
Преобразует примитивный стрим в объектный.


    DoubleStream.of(0.1, Math.PI)
        .boxed()
        .map(Object::getClass)
        .forEach(System.out::println);
    // class java.lang.Double
    // class java.lang.Double



6.3. Терминальные операторы


void forEach(Consumer action)
Выполняет указанное действие для каждого элемента стрима.


    Stream.of(120, 410, 85, 32, 314, 12)
        .forEach(x -> System.out.format("%s, ", x));
    // 120, 410, 85, 32, 314, 12
void forEachOrdered(Consumer action)
Тоже выполняет указанное действие для каждого элемента стрима, но перед этим добивается правильного порядка вхождения элементов. Используется для параллельных стримов, когда нужно получить правильную последовательность элементов.


    IntStream.range(0, 100000)
        .parallel()
        .filter(x -> x % 10000 == 0)
        .map(x -> x / 10000)
        .forEach(System.out::println);
    // 5, 6, 7, 3, 4, 8, 0, 9, 1, 2
     
    IntStream.range(0, 100000)
        .parallel()
        .filter(x -> x % 10000 == 0)
        .map(x -> x / 10000)
        .forEachOrdered(System.out::println);
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9



long count()
Возвращает количество элементов стрима.


    long count = IntStream.range(0, 10)
        .flatMap(x -> IntStream.range(0, x))
        .count();
    System.out.println(count);
    // 45
     
    System.out.println(
        IntStream.rangeClosed(-3, )
            .count()
    );
    // 10
     
    System.out.println(
        Stream.of(0, 2, 9, 13, 5, 11)
            .(x -> x 2)
            .filter(x -> x % 2 == 1)
            .count()
    );
    // 0



R collect(Collector collector)
Один из самых мощных операторов Stream API. С его помощью можно собрать все элементы в список, множество или другую коллекцию, сгруппировать элементы по какому-нибудь критерию, объединить всё в строку и т.д.. В классе java.util.stream.Collectors очень много методов на все случаи жизни, мы рассмотрим их позже. При желании можно написать свой коллектор, реализовав интерфейс Collector.


    List<Integer> list = Stream.of(1, 2, 3)
        .collect(Collectors.toList());
    // list: [1, 2, 3]
     
    String s = Stream.of(1, 2, 3)
        .map(String::valueOf)
        .collect(Collectors.joining("-", "<", ">"));
    // s: "<1-2-3>"



R collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)
То же, что и collect(collector), только параметры разбиты для удобства. Если нужно быстро сделать какую-то операцию, нет нужды реализовывать интерфейс Collector, достаточно передать три лямбда-выражения.

supplier должен поставлять новые объекты (контейнеры), например new ArrayList(), accumulator добавляет элемент в контейнер, combiner необходим для параллельных стримов и объединяет части стрима воедино.


    List<String> list = Stream.of("a", "b", "c", "d")
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    // list: ["a", "b", "c", "d"]



Object[] toArray()
Возвращает нетипизированный массив с элементами стрима.

A[] toArray(IntFunction<A[]> generator)
Аналогично, только возвращает типизированный массив.



    String[] elements = Stream.of("a", "b", "c", "d")
        .toArray(String[]::new);
    // elements: ["a", "b", "c", "d"]



List<T> toList()
Наконец-то добавлен в Java 16. Возвращает список, подобно collect(Collectors.toList()). Отличие в том, что теперь возвращаемый список гарантированно нельзя будет модифицировать. Любое добавление или удаление элементов в полученный список будет сопровождаться исключением UnsupportedOperationException.



    List<String> elements = Stream.of("a", "b", "c", "d")
            .map(String::toUpperCase)
            .toList();
    // elements: ["A", "B", "C", "D"]



T reduce(T identity, BinaryOperator accumulator)
U reduce(U identity, BiFunction accumulator, BinaryOperator combiner)
Ещё один полезный оператор. Позволяет преобразовать все элементы стрима в один объект. Например, посчитать сумму всех элементов, либо найти минимальный элемент.

Сперва берётся объект identity и первый элемент стрима, применяется функция accumulator и identity становится её результатом. Затем всё продолжается для остальных элементов.

    int sum = Stream.of(1, 2, 3, 4, 5)
        .reduce(10, (acc, x) -> acc + x);
    // sum: 25
Optional reduce(BinaryOperator accumulator)
Этот метод отличается тем, что у него нет начального объекта identity. В качестве него служит первый элемент стрима. Поскольку стрим может быть пустой и тогда identity объект не присвоится, то результатом функции служит Optional, позволяющий обработать и эту ситуацию, вернув Optional.empty().


    Optional<Integer> result = Stream.<Integer>empty()
        .reduce((acc, x) -> acc + x);
    System.out.println(result.isPresent());
    // false
     
    Optional<Integer> sum = Stream.of(1, 2, 3, 4, 5)
        .reduce((acc, x) -> acc + x);
    System.out.println(sum.get());
    // 15


    int sum = IntStream.of(2, 4, 6, 8)
        .reduce(, (acc, x) -> acc + x);
    // sum: 25
     
    int product = IntStream.range(0, 10)
        .filter(x -> x++ % 4 == 0)
        .reduce((acc, x) -> acc * x)
        .getAsInt();
    // product:



Optional min(Comparator comparator)
Optional max(Comparator comparator)
Поиск минимального/максимального элемента, основываясь на переданном компараторе. Внутри вызывается reduce:

    reduce((a, b) -> comparator.compare(a, b) <= 0 ? a : b));
    reduce((a, b) -> comparator.compare(a, b) >= 0 ? a : b));




    int min = Stream.of(20, 11, 45, 78, 13)
        .min(Integer::compare).get();
    // min: 11
     
    int max = Stream.of(20, 11, 45, 78, 13)
        .max(Integer::compare).get();
    // max: 78



Optional findAny()
Возвращает первый попавшийся элемент стрима. В параллельных стримах это может быть действительно любой элемент, который лежал в разбитой части последовательности.

Optional findFirst()
Гарантированно возвращает первый элемент стрима, даже если стрим параллельный.

Если нужен любой элемент, то для параллельных стримов быстрее будет работать findAny().


    int anySeq = IntStream.range(4, 65536)
        .findAny()
        .getAsInt();
    // anySeq: 4
     
    int firstSeq = IntStream.range(4, 65536)
        .findFirst()
        .getAsInt();
    // firstSeq: 4
     
    int anyParallel = IntStream.range(4, 65536)
        .parallel()
        .findAny()
        .getAsInt();
    // anyParallel: 32770
     
    int firstParallel = IntStream.range(4, 65536)
        .parallel()
        .findFirst()
        .getAsInt();
    // firstParallel: 4



boolean allMatch(Predicate predicate)
Возвращает true, если все элементы стрима удовлетворяют условию predicate. Если встречается какой-либо элемент, для которого результат вызова функции-предиката будет false, то оператор перестаёт просматривать элементы и возвращает false.

    boolean result = Stream.of(1, 2, 3, 4, 5)
        .allMatch(x -> x <= 7);
    // result: true


    boolean result = Stream.of(120, 410, 85, 32, 314, 12)
        .allMatch(x -> x % 2 == 0);
    // result:



boolean anyMatch(Predicate predicate)
Возвращает true, если хотя бы один элемент стрима удовлетворяет условию predicate. Если такой элемент встретился, нет смысла продолжать перебор элементов, поэтому сразу возвращается результат.

    boolean result = Stream.of(1, 2, 3, 4, 5)
        .anyMatch(x -> x == 3);
    // result: true

    boolean result = Stream.of(1, 2, 3, 4, 5)
        .anyMatch(x -> x == 8);
    // result: false

    boolean result = Stream.of(120, 410, 85, 32, 314, 12)
        .anyMatch(x -> x % 22 == 0);
    // result:
boolean noneMatch(Predicate predicate)
Возвращает true, если, пройдя все элементы стрима, ни один не удовлетворил условию predicate. Если встречается какой-либо элемент, для которого результат вызова функции-предиката будет true, то оператор перестаёт перебирать элементы и возвращает false.

    boolean result = Stream.of(1, 2, 3, 4, 5)
        .noneMatch(x -> x == 9);
    // result: true

    boolean result = Stream.of(1, 2, 3, 4, 5)
        .noneMatch(x -> x == 3);
    // result: false


    boolean result = Stream.of(120, 410, 86, 32, 314, 12)
        .noneMatch(x -> x % 2 == 1);
    // result:



OptionalDouble average()
Только для примитивных стримов. Возвращает среднее арифметическое всех элементов. Либо Optional.empty, если стрим пуст.

    double result = IntStream.range(2, 16)
        .average()
        .getAsDouble();
    // result: 8.5



sum()
Возвращает сумму элементов примитивного стрима. Для IntStream результат будет типа int, для LongStream — long, для DoubleStream — double.

    long result = LongStream.range(2, 16)
        .sum();
    // result: 119



IntSummaryStatistics summaryStatistics()
Полезный метод примитивных стримов. Позволяет собрать статистику о числовой последовательности стрима, а именно: количество элементов, их сумму, среднее арифметическое, минимальный и максимальный элемент.


    LongSummaryStatistics stats = LongStream.range(2, 16)
        .summaryStatistics();
    System.out.format("  count: %d%n", stats.getCount());
    System.out.format("    sum: %d%n", stats.getSum());
    System.out.format("average: %.1f%n", stats.getAverage());
    System.out.format("    min: %d%n", stats.getMin());
    System.out.format("    max: %d%n", stats.getMax());
    //   count: 14
    //     sum: 119
    // average: 8,5
    //     min: 2
    //     max: 15


7. Методы Collectors

toList()
Самый распространённый метод. Собирает элементы в List.


toSet()
Собирает элементы в множество.


toCollection(Supplier collectionFactory)
Собирает элементы в заданную коллекцию. Если нужно конкретно указать, какой List, Set или другую коллекцию мы хотим использовать, то этот метод поможет.


    Deque<Integer> deque = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.toCollection(ArrayDeque::new));
     
    Set<Integer> set = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.toCollection(LinkedHashSet::new));



toMap(Function keyMapper, Function valueMapper)
Собирает элементы в Map. Каждый элемент преобразовывается в ключ и в значение, основываясь на результате функций keyMapper и valueMapper соответственно. Если нужно вернуть тот же элемент, что и пришел, то можно передать Function.identity().


    Map<Integer, Integer> map1 = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.toMap(
            Function.identity(),
            Function.identity()
        ));
    // {1=1, 2=2, 3=3, 4=4, 5=5}
     
    Map<Integer, String> map2 = Stream.of(1, 2, 3)
        .collect(Collectors.toMap(
            Function.identity(),
            i -> String.format("%d * 2 = %d", i, i * 2)
        ));
    // {1="1 * 2 = 2", 2="2 * 2 = 4", 3="3 * 2 = 6"}
     
    Map<Character, String> map3 = Stream.of(50, 54, 55)
        .collect(Collectors.toMap(
            i -> (char) i.intValue(),
            i -> String.format("<%d>", i)
        ));
    // {'2'="<50>", '6'="<54>", '7'="<55>"}


toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
Аналогичен первой версии метода, только в случае, когда встречается два одинаковых ключа, позволяет объединить значения.


    Map<Integer, String> map4 = Stream.of(50, 55, 69, 20, 19, 52)
        .collect(Collectors.toMap(
            i -> i % 5,
            i -> String.format("<%d>", i),
            (a, b) -> String.join(", ", a, b)
        ));
    // {0="<50>, <55>, <20>", 2="<52>", 4="<64>, <19>"}

В данном случае, для чисел 50, 55 и 20, ключ одинаков и равен 0, поэтому значения накапливаются. Для 64 и 19 аналогично.

toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)
Всё то же, только позволяет указывать, какой именно класс Map использовать.


    Map<Integer, String> map5 = Stream.of(50, 55, 69, 20, 19, 52)
        .collect(Collectors.toMap(
            i -> i % 5,
            i -> String.format("<%d>", i),
            (a, b) -> String.join(", ", a, b),
            LinkedHashMap::new
        ));
    // {0=<50>, <55>, <20>, 4=<69>, <19>, 2=<52>}


Отличие этого примера от предыдущего в том, что теперь сохраняется порядок, благодаря LinkedHashList.

toConcurrentMap(Function keyMapper, Function valueMapper)
toConcurrentMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
toConcurrentMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)
Всё то же самое, что и toMap, только работаем с ConcurrentMap.


collectingAndThen(Collector downstream, Function finisher)
Собирает элементы с помощью указанного коллектора, а потом применяет к полученному результату функцию.


    List<Integer> list = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.collectingAndThen(
            Collectors.toList(),
            Collections::unmodifiableList));
    System.out.println(list.getClass());
    // class java.util.Collections$UnmodifiableRandomAccessList
     
    List<String> list2 = Stream.of("a", "b", "c", "d")
        .collect(Collectors.collectingAndThen(
                Collectors.toMap(Function.identity(), s -> s + s),
                map -> map.entrySet().stream()))
        .map(e -> e.toString())
        .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::unmodifiableList));
    list2.forEach(System.out::println);
    // a=aa
    // b=bb
    // c=cc
    // d=dd



joining()
joining(CharSequence delimiter)
joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
Собирает элементы, реализующие интерфейс CharSequence, в единую строку. Дополнительно можно указать разделитель, а также префикс и суффикс для всей последовательности.


    String s1 = Stream.of("a", "b", "c", "d")
        .collect(Collectors.joining());
    System.out.println(s1);
    // abcd
     
    String s2 = Stream.of("a", "b", "c", "d")
        .collect(Collectors.joining("-"));
    System.out.println(s2);
    // a-b-c-d
     
    String s3 = Stream.of("a", "b", "c", "d")
        .collect(Collectors.joining(" -> ", "[ ", " ]"));
    System.out.println(s3);
    // [ a -> b -> c -> d ]



summingInt(ToIntFunction mapper)
summingLong(ToLongFunction mapper)
summingDouble(ToDoubleFunction mapper)
Коллектор, который преобразовывает объекты в int/long/double и подсчитывает сумму.

averagingInt(ToIntFunction mapper)
averagingLong(ToLongFunction mapper)
averagingDouble(ToDoubleFunction mapper)
Аналогично, но со средним значением.

summarizingInt(ToIntFunction mapper)
summarizingLong(ToLongFunction mapper)
summarizingDouble(ToDoubleFunction mapper)
Аналогично, но с полной статистикой.


    Integer sum = Stream.of("1", "2", "3", "4")
        .collect(Collectors.summingInt(Integer::parseInt));
    System.out.println(sum);
    // 10
     
    Double average = Stream.of("1", "2", "3", "4")
        .collect(Collectors.averagingInt(Integer::parseInt));
    System.out.println(average);
    // 2.5
     
    DoubleSummaryStatistics stats = Stream.of("1.1", "2.34", "3.14", "4.04")
        .collect(Collectors.summarizingDouble(Double::parseDouble));
    System.out.println(stats);
    // DoubleSummaryStatistics{count=4, sum=10.620000, min=1.100000, average=2.655000, max=4.040000}


Все эти методы и несколько последующих, зачастую используются в качестве составных коллекторов для группировки или collectingAndThen. В том виде, в котором они показаны на примерах используются редко. Я лишь показываю пример, что они возвращают, чтобы было понятнее.


counting()
Подсчитывает количество элементов.


    Long count = Stream.of("1", "2", "3", "4")
        .collect(Collectors.counting());
    System.out.println(count);
    // 4



filtering(Predicate predicate, Collector downstream)
mapping(Function mapper, Collector downstream)
flatMapping(Function downstream)
reducing(BinaryOperator op)
reducing(T identity, BinaryOperator op)
reducing(U identity, Function mapper, BinaryOperator op)
Специальная группа коллекторов, которая применяет операции filter, map, flatMap и reduce. filtering и flatMapping появились в Java 9.


    List<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6)
        .collect(Collectors.filtering(
            x -> x % 2 == 0,
            Collectors.toList()));
    // 2, 4, 6
     
    String s1 = Stream.of(1, 2, 3, 4, 5, 6)
        .collect(Collectors.filtering(
            x -> x % 2 == 0,
            Collectors.mapping(
                x -> Integer.toString(x),
                Collectors.joining("-")
            )
        ));
    // 2-4-6
     
    String s2 = Stream.of(2, 0, 1, 3, 2)
        .collect(Collectors.flatMapping(
            x -> IntStream.range(0, x).mapToObj(Integer::toString),
            Collectors.joining(", ")
        ));
    // 0, 1, 0, 0, 1, 2, 0, 1
     
    int value = Stream.of(1, 2, 3, 4, 5, 6)
        .collect(Collectors.reducing(
            0, (a, b) -> a + b
        ));
    // 21
    String s3 = Stream.of(1, 2, 3, 4, 5, 6)
        .collect(Collectors.reducing(
            "", x -> Integer.toString(x), (a, b) -> a + b
        ));
    // 123456



minBy(Comparator comparator)
maxBy(Comparator comparator)
Поиск минимального/максимального элемента, основываясь на заданном компараторе.


    Optional<String> min = Stream.of("ab", "c", "defgh", "ijk", "l")
        .collect(Collectors.minBy(Comparator.comparing(String::length)));
    min.ifPresent(System.out::println);
    // c
     
    Optional<String> max = Stream.of("ab", "c", "defgh", "ijk", "l")
        .collect(Collectors.maxBy(Comparator.comparing(String::length)));
    max.ifPresent(System.out::println);
    // defgh



groupingBy(Function classifier)
groupingBy(Function classifier, Collector downstream)
groupingBy(Function classifier, Supplier mapFactory, Collector downstream)
Группирует элементы по критерию, сохраняя результат в Map. Вместе с представленными выше агрегирующими коллекторами, позволяет гибко собирать данные. Подробнее о комбинировании в разделе Примеры.

groupingByConcurrent(Function classifier)
groupingByConcurrent(Function classifier, Collector downstream)
groupingByConcurrent(Function classifier, Supplier mapFactory, Collector downstream)
Аналогичный набор методов, только сохраняет в ConcurrentMap.


    Map<Integer, List<String>> map1 = Stream.of(
        "ab", "c", "def", "gh", "ijk", "l", "mnop")
        .collect(Collectors.groupingBy(String::length));
    map1.entrySet().forEach(System.out::println);
    // 1=[c, l]
    // 2=[ab, gh]
    // 3=[def, ijk]
    // 4=[mnop]
     
    Map<Integer, String> map2 = Stream.of(
        "ab", "c", "def", "gh", "ijk", "l", "mnop")
        .collect(Collectors.groupingBy(
            String::length,
            Collectors.mapping(
                String::toUpperCase,
                Collectors.joining())
        ));
    map2.entrySet().forEach(System.out::println);
    // 1=CL
    // 2=ABGH
    // 3=DEFIJK
    // 4=MNOP
     
    Map<Integer, List<String>> map3 = Stream.of(
        "ab", "c", "def", "gh", "ijk", "l", "mnop")
        .collect(Collectors.groupingBy(
            String::length,
            LinkedHashMap::new,
            Collectors.mapping(
                String::toUpperCase,
                Collectors.toList())
        ));
    map3.entrySet().forEach(System.out::println);
    // 2=[AB, GH]
    // 1=[C, L]
    // 3=[DEF, IJK]
    // 4=[MNOP]



partitioningBy(Predicate predicate)
partitioningBy(Predicate predicate, Collector downstream)
Ещё один интересный метод. Разбивает последовательность элементов по какому-либо критерию. В одну часть попадают все элементы, которые удовлетворяют переданному условию, во вторую — все, которые не удовлетворяют.


    Map<Boolean, List<String>> map1 = Stream.of(
        "ab", "c", "def", "gh", "ijk", "l", "mnop")
        .collect(Collectors.partitioningBy(s -> s.length() <= 2));
    map1.entrySet().forEach(System.out::println);
    // false=[def, ijk, mnop]
    // true=[ab, c, gh, l]
     
    Map<Boolean, String> map2 = Stream.of(
        "ab", "c", "def", "gh", "ijk", "l", "mnop")
        .collect(Collectors.partitioningBy(
            s -> s.length() <= 2,
            Collectors.mapping(
                String::toUpperCase,
                Collectors.joining())
        ));
    map2.entrySet().forEach(System.out::println);
    // false=DEFIJKMNOP
    // true=ABCGHL



8. Collector

Интерфейс java.util.stream.Collector служит для сбора элементов стрима в некоторый мутабельный контейнер. Он состоит из таких методов:
- Supplier<A> supplier() — функция, которая создаёт экземпляры контейнеров.
- BiConsumer<A,T> accumulator() — функция, которая кладёт новый элемент в контейнер.
- BinaryOperator<A> combiner() — функция, которая объединяет два контейнера в один. В параллельных стримах каждая часть может собираться в отдельный экземпляр контейнера и в итоге необходимо их объединять в один результирующий.
- Function<A,R> finisher() — функция, которая преобразовывает весь контейнер в конечный результат. Например, можно обернуть List в Collections.unmodifiableList.
- Set<Characteristics> characteristics() — возвращает характеристики коллектора, чтобы внутренняя реализация знала, с чем имеет дело. Например, можно указать, что коллектор поддерживает многопоточность.

Характеристики:
- CONCURRENT — коллектор поддерживает многопоточность, а значит отдельные части стрима могут быть успешно положены в контейнер из другого потока.
- UNORDERED — коллектор не зависит от порядка поступаемых элементов.
- IDENTITY_FINISH — функция finish() имеет стандартную реализацию (Function.identity()), а значит её можно не вызывать.

8.1. Реализация собственного коллектора

Прежде чем писать свой коллектор, нужно убедиться, что задачу нельзя решить при помощи комбинации стандартных коллекторов.
К примеру, если нужно собрать лишь уникальные элементы в список, то можно собрать элементы сначала в LinkedHashSet, чтобы сохранился порядок, а потом все элементы добавить в ArrayList. Комбинация collectingAndThen с toCollection и функцией, передающей полученный Set в конструктор ArrayList, делает то, что задумано:


    Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
        .collect(Collectors.collectingAndThen(
            Collectors.toCollection(LinkedHashSet::new),
            ArrayList::new));
    // 1 2 3 9 5 4 8


А вот если задача состоит в том, чтобы собрать уникальные элементы в одну часть, а повторяющиеся в другую, например в Map<Boolean, List>, то при помощи partitioningBy получится не очень красиво:


    final Set<Integer> elements = new HashSet<>();
    Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
        .collect(Collectors.partitioningBy(elements::add))
        .forEach((isUnique, list) -> System.out.format("%s: %s%n", isUnique ? "unique" : "repetitive", list));

Здесь приходится создавать Set и в предикате коллектора его использовать, что нежелательно. Можно превратить лямбду в анонимную функцию, но это ещё хуже:


    new Predicate<Integer>() {
        final Set<Integer> elements = new HashSet<>();
        @Override
        public boolean test(Integer t) {
            return elements.add(t);
        }
    }


Для создания своего коллектора есть два пути:
1. Создать класс, реализующий интерфейс Collector.
2. Воспользоваться фабрикой Collector.of.
   Если нужно сделать что-то универсальное, чтобы работало для любых типов, то есть использовать дженерики, то во втором варианте можно просто сделать статическую функцию, а внутри использовать Collector.of.

Вот полученный коллектор.


    public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningByUniqueness() {
        return Collector.<T, Map.Entry<List<T>, Set<T>>, Map<Boolean, List<T>>>of(
            () -> new AbstractMap.SimpleImmutableEntry<>(
                        new ArrayList<T>(), new LinkedHashSet<>()),
            (c, e) -> {
                if (!c.getValue().add(e)) {
                    c.getKey().add(e);
                }
            },
            (c1, c2) -> {
                c1.getKey().addAll(c2.getKey());
                for (T e : c2.getValue()) {
                    if (!c1.getValue().add(e)) {
                        c1.getKey().add(e);
                    }
                }
                return c1;
            },
            c -> {
                Map<Boolean, List<T>> result = new HashMap<>(2);
                result.put(Boolean.FALSE, c.getKey());
                result.put(Boolean.TRUE, new ArrayList<>(c.getValue()));
                return result;
            });
    }

Давайте теперь разбираться.

Интерфейс Collector объявлен так:
interface Collector<T, A, R>
T - тип входных элементов.
A - тип контейнера, в который будут поступать элементы.
R - тип результата.

Сигнатура метода, возвращающего коллектор такова:
public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningByUniqueness()
Он принимает элементы типа T, возвращает Map<Boolean, List<T>>, как и partitioningBy. Знак вопроса (джокер) в среднем параметре говорит о том, что внутренний тип реализации для публичного API не важен. Многие методы класса Collectors содержат джокер в качестве типа контейнера.

return Collector.<T, Map.Entry<List<T>, Set<T>>, Map<Boolean, List<T>>>of
Вот здесь уже пришлось указать тип контейнера. Так как в Java нет класса Pair или Tuple, то два разных типа можно положить в Map.Entry.



    // supplier
    () -> new AbstractMap.SimpleImmutableEntry<>(
            new ArrayList<>(), new LinkedHashSet<>())

Контейнером будет AbstractMap.SimpleImmutableEntry. В ключе будет список повторяющихся элементов, в значении — множество с уникальными элементами.

    // accumulator
    (c, e) -> {
        if (!c.getValue().add(e)) {
            c.getKey().add(e);
        }
    }

Здесь всё просто. Если элемент нельзя добавить во множество (по причине того, что там уже есть такой элемент), то добавляем его в список повторяющихся элементов.



    // combiner
    (c1, c2) -> {
        c1.getKey().addAll(c2.getKey());
        for (T e : c2.getValue()) {
            if (!c1.getValue().add(e)) {
                c1.getKey().add(e);
            }
        }
        return c1;
    }

Нужно объединить два Map.Entry. Списки повторяющихся элементов можно объединить вместе, а вот с уникальными элементами так просто не выйдет — нужно пройтись поэлементно и повторить всё то, что делалось в функции-аккумуляторе. Кстати, лямбду-аккумулятор можно присвоить переменной и тогда цикл можно превратить в c2.getValue().forEach(e -> accumulator.accept(c1, e));



    // finisher
    c -> {
        Map<Boolean, List<T>> result = new HashMap<>(2);
        result.put(Boolean.FALSE, c.getKey());
        result.put(Boolean.TRUE, new ArrayList<>(c.getValue()));
        return result;
    }

Наконец, возвращаем необходимый результат. В map.get(Boolean.TRUE) будут уникальные, а в map.get(Boolean.FALSE) — повторяющиеся элементы.


    Map<Boolean, List<Integer>> map;
    map = Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
        .collect(partitioningByUniqueness());
    // {false=[1, 2, 3, 2], true=[1, 2, 3, 9, 5, 4, 8]}


Хорошей практикой является создание коллекторов, которые принимают ещё один коллектор и зависят от него. Например, можно будет складывать элементы не только в List, но и в любую другую коллекцию (Collectors.toCollection), либо в строку (Collectors.joining).


    public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningByUniqueness(
            Collector<? super T, A, D> downstream) {
        class Holder<A, B> {
            final A unique, repetitive;
            final B set;
            Holder(A unique, A repetitive, B set) {
                this.unique = unique;
                this.repetitive = repetitive;
                this.set = set;
            }
        }
        BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
        BinaryOperator<A> downstreamCombiner = downstream.combiner();
        BiConsumer<Holder<A, Set<T>>, T> accumulator = (t, element) -> {
            A container = t.set.add(element) ? t.unique : t.repetitive;
            downstreamAccumulator.accept(container, element);
        };
        return Collector.<T, Holder<A, Set<T>>, Map<Boolean, D>>of(
                () -> new Holder<>(
                    downstream.supplier().get(),
                    downstream.supplier().get(),
                    new HashSet<>() ),
                accumulator,
                (t1, t2) -> {
                    downstreamCombiner.apply(t1.repetitive, t2.repetitive);
                    t2.set.forEach(e -> accumulator.accept(t1, e));
                    return t1;
                },
                t -> {
                    Map<Boolean, D> result = new HashMap<>(2);
                    result.put(Boolean.FALSE, downstream.finisher().apply(t.repetitive));
                    result.put(Boolean.TRUE, downstream.finisher().apply(t.unique));
                    t.set.clear();
                    return result;
                });
    }

Алгоритм остался тем же, только теперь уже нельзя во второй контейнер сразу же складывать уникальные элементы, приходится создавать новый set. Для удобства также добавлен класс Holder, который хранит два контейнера для уникальных и повторяющихся элементов, а также само множество.

Все операции теперь нужно проводить через переданный коллектор, именуемый downstream. Именно он сможет поставить контейнер нужного типа (downstream.supplier().get()), добавить элемент в этот контейнер (downstream.accumulator().accept(container, element)), объединить контейнеры и создать окончательный результат.


    Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
        .map(String::valueOf)
        .collect(partitioningByUniqueness(Collectors.joining("-")))
        .forEach((isUnique, str) -> System.out.format("%s: %s%n", isUnique ? "unique" : "repetitive", str));
    // repetitive: 1-2-3-2
    // unique: 1-2-3-9-5-4-8


Кстати, первую реализацию метода без аргументов можно теперь заменить на:


    public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningByUniqueness() {
        return partitioningByUniqueness(Collectors.toList());
    }



9. Spliterator

Пришло время немного углубиться в работу Stream API изнутри. Элементы стримов нужно не только итерировать, но ещё и разделять на части и отдавать другим потокам. За итерацию и разбиение отвечает Spliterator. Он даже звучит как Iterator, только с приставкой Split — разделять.

Методы интерфейса:
- trySplit — как следует из названия, пытается разделить элементы на две части. Если это сделать не получается, либо элементов недостаточно для разделения, то вернёт null. В остальных случаях возвращает ещё один Spliterator с частью данных.
- tryAdvance(Consumer action) — если имеются элементы, для которых можно применить действие, то оно применяется и возвращает true, в противном случае возвращается false, но действие не выполняется.
- estimateSize() — возвращает примерное количество элементов, оставшихся для обработки, либо Long.MAX_VALUE, если стрим бесконечный или посчитать количество невозможно.
- characteristics() — возвращает характеристики сплитератора.

9.1. Характеристики

В методе sorted и distinct было упомянуто, что если стрим помечен как отсортированный или содержащий уникальные элементы, то соответствующие операции проводиться не будут. Вот характеристики сплитератора и влияют на это.

- DISTINCT — все элементы уникальны. Сплитераторы всех реализаций Set содержат эту характеристику.
- SORTED — все элементы отсортированы.
- ORDERED — порядок имеет значение. Сплитераторы большинства коллекций содержат эту характеристику, а HashSet, к примеру, нет.
- SIZED — количество элементов точно известно.
- SUBSIZED — количество элементов каждой разбитой части точно известно.
- NONNULL — в элементах не встречается null. Некоторые коллекции из java.util.concurrent, в которые нельзя положить null, содержат эту характеристику.
- IMMUTABLE — источник является иммутабельным и в него нельзя больше добавить элементов, либо удалить их.
- CONCURRENT — источник лоялен к любым изменениям.

Разумеется, характеристики могут быть изменены при выполнении цепочки операторов. Например, после sorted добавляется характеристика SORTED, после filter теряется SIZED и т.д.

9.2. Жизненный цикл сплитератора

Чтобы понять когда и как сплитератор вызывает тот или иной метод, давайте создадим обёртку, которая логирует все вызовы. Чтобы из сплитератора создать стрим, используется класс StreamSupport.

    long count = StreamSupport.stream(
        Arrays.asList(0, 1, 2, 3).spliterator(), true)
        .count();

list-spliterator.png
На рисунке показан один из возможных вариантов работы сплитератора. characteristics везде возвращает ORDERED | SIZED | SUBSIZED, так как в List порядок имеет значение, количество элементов и всех разбитых кусков также известно. trySplit делит последовательность пополам, но не обязательно каждая часть будет отправлена новому потоку. В параллельном стриме новый поток может и не создаться, т.к. всё успевает обработаться в главном потоке. В данном же случае, новый поток успевал обработать части до того, как это делал главный поток.



    Spliterator<Integer> s = IntStream.range(0, 4)
        .boxed()
        .collect(Collectors.toSet())
        .spliterator();
    long count = StreamSupport.stream(s, true).count();

Здесь у сплитератора характеристикой будет SIZED | DISTINCT, а вот у каждой части характеристика SIZED теряется, остаётся только DISTINCT, потому что нельзя поделить множество так, чтобы размер каждой части был известен.
В случае с Set было три вызова trySplit, первый якобы делил элементы поровну, после двух других каждая из частей возврашала estimateSize: 1, однако во всех, кроме одной попытка вызвать tryAdvance не увенчалась успехом — возвращался false. А вот на одном из частей, который для estimateSize также возвращал 1, было 4 успешных вызова tryAdvance. Это и подтверждает тот факт, что estimateSize не обязательно должен возвращать действительное число элементов.

    Arrays.spliterator(new int[] {0, 1, 2, 3});
    Stream.of(0, 1, 2, 3).spliterator();

Ситуация аналогична работе List, только характеристики возвращали ORDERED | SIZED | SUBSIZED | IMMUTABLE.

    Stream.of(0, 1, 2, 3).distinct().spliterator();

Здесь trySplit возвращал null, а значит поделить последовательно не представлялось возможным. Иерархия вызовов:


    [main] characteristics: ORDERED | DISTINCT
    [main] estimateSize: 4
    [main] trySplit: null
    [main] characteristics: ORDERED | DISTINCT
    [main] tryAdvance: true
    [main] tryAdvance: true
    [main] tryAdvance: true
    [main] tryAdvance: true
    [main] tryAdvance: false
    count: 4


    Stream.of(0, 1, 2, 3)
        .distinct()
        .map(x -> x + 1)
        .spliterator();

Всё, как и выше, только теперь после применения оператора map, флаг DISTINCT исчез.

9.3. Реализация сплитератора

Для правильной реализации сплитератора нужно продумать, как сделать разбиение и обозначить характеристики стрима. Давайте напишем сплитератор, генерирующий последовательность чисел Фибоначчи.
Для упрощения задачи нам будет известно максимальное количество элементов для генерирования. А значит мы можем разделять последовательность пополам, а потом быстро просчитывать нужные числа по новому индексу.
Осталось определиться с характеристиками. Мы уже решили, что размер последовательности нам будет известен, а значит будет известен и размер каждой разбитой части. Порядок будет важен, так что без флага ORDERED не обойтись. Последовательность Фибоначчи также отсортирована — каждый последующий элемент всегда не меньше предыдущего.
А вот с флагом DISTINCT, кажется, промах. 0 1 1 2 3, две единицы повторяются, а значит не видать нам этого флага?
На самом деле ничто нам не мешает просчитывать флаги автоматически. Если часть последовательности не будет затрагивать начальные индексы, то этот флаг можно выставить.


    int distinct = (index >= 2) ? DISTINCT : 0;
    return ORDERED | distinct | SIZED | SUBSIZED | IMMUTABLE | NONNULL;


Полная реализация класса:


    import java.math.BigInteger;
    import java.util.Spliterator;
    import java.util.function.Consumer;
     
    public class FibonacciSpliterator implements Spliterator<BigInteger> {
     
        private final int fence;
        private int index;
        private BigInteger a, b;
     
        public FibonacciSpliterator(int fence) {
            this(0, fence);
        }
     
        protected FibonacciSpliterator(int start, int fence) {
            this.index = start;
            this.fence = fence;
            recalculateNumbers(start);
        }
     
        private void recalculateNumbers(int start) {
            a = fastFibonacciDoubling(start);
            b = fastFibonacciDoubling(start + 1);
        }
     
        @Override
        public boolean tryAdvance(Consumer<? super BigInteger> action) {
            if (index >= fence) {
                return false;
            }
            action.accept(a);
            BigInteger c = a.add(b);
            a = b;
            b = c;
            index++;
            return true;
        }
     
        @Override
        public FibonacciSpliterator trySplit() {
            int lo = index;
            int mid = (lo + fence) >>> 1;
            if (lo >= mid) {
                return null;
            }
            index = mid;
            recalculateNumbers(mid);
            return new FibonacciSpliterator(lo, mid);
        }
     
        @Override
        public long estimateSize() {
            return fence - index;
        }
     
        @Override
        public int characteristics() {
            int distinct = (index >= 2) ? DISTINCT : 0;
            return ORDERED | distinct | SIZED | SUBSIZED | IMMUTABLE | NONNULL;
        }
     
        /*
         * https://www.nayuki.io/page/fast-fibonacci-algorithms
         */
        public static BigInteger fastFibonacciDoubling(int n) {
            BigInteger a = BigInteger.ZERO;
            BigInteger b = BigInteger.ONE;
            for (int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
                BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
                BigInteger e = a.multiply(a).add(b.multiply(b));
                a = d;
                b = e;
                if ((n & bit) != 0) {
                    BigInteger c = a.add(b);
                    a = b;
                    b = c;
                }
            }
            return a;
        }
    }


Вот как разбиваются теперь элементы параллельного стрима:

    StreamSupport.stream(new FibonacciSpliterator(7), true)
        .count();

fibonaccispliterator.png

    StreamSupport.stream(new FibonacciSpliterator(500), true)
        .count();

fibonaccispliterator500.png

10. Другие способы создания источников

Стрим из сплитератора — это самый эффективный способ создания стрима, но кроме него есть и другие способы.

10.1. Стрим из итератора

Благодаря классу Spliterators, можно преобразовать любой итератор в сплитератор. Вот пример создания стрима из итератора, генерирующего бесконечную последовательность чисел Фибоначчи.


    public class FibonacciIterator implements Iterator<BigInteger> {
     
        private BigInteger a = BigInteger.ZERO;
        private BigInteger b = BigInteger.ONE;
     
        @Override
        public boolean hasNext() {
            return true;
        }
     
        @Override
        public BigInteger next() {
            BigInteger result = a;
            a = b;
            b = result.add(b);
            return result;
        }
    }
     
    StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(
            new FibonacciIterator(),
            Spliterator.ORDERED | Spliterator.SORTED),
        false /* is parallel*/)
        .limit(10)
        .forEach(System.out::println);


10.2. Stream.iterate + map

Можно воспользоваться двумя операторами: iterate + map, чтобы создать всё тот же стрим из чисел Фибоначчи.


    Stream.iterate(
        new BigInteger[] { BigInteger.ZERO, BigInteger.ONE },
        t -> new BigInteger[] { t[1], t[0].add(t[1]) })
        .map(t -> t[0])
        .limit(10)
        .forEach(System.out::println);


Для удобства можно обернуть всё в метод и вызывать fibonacciStream().limit(10).forEach(...).

10.3. IntStream.range + map

Ещё один гибкий и удобный способ создать стрим. Если у вас есть какие-то данные, которые можно получить по индексу, то можно создать числовой промежуток при помощи оператора range, затем поэлементно с помощью него обращаться к данным через map/mapToObj.


    IntStream.range(0, 200)
        .mapToObj(i -> fibonacci(i))
        .forEach(System.out::println);
     
    JSONArray arr = ...
    IntStream.range(0, arr.length())
        .mapToObj(JSONArray::getJSONObject)
        .map(obj -> ...)
        .forEach(System.out::println);



11. Примеры

Прежде чем перейти к более приближенным к жизни примерам, стоит сказать, что если код уже написан без стримов и работает хорошо, не нужно сломя голову всё переписывать. Также бывает ситуации, когда красиво реализовать задачу с использованием Stream API не получается, в таком случае смиритесь и не тяните стримы за уши.

Дан массив аргументов. Нужно получить Map, где каждому ключу будет соответствовать своё значение.


    String[] arguments = {"-i", "in.txt", "--limit", "40", "-d", "1", "-o", "out.txt"};
    Map<String, String> argsMap = new LinkedHashMap<>(arguments.length / 2);
    for (int i = 0; i < arguments.length; i += 2) {
        argsMap.put(arguments[i], arguments[i + 1]);
    }
    argsMap.forEach((key, value) -> System.out.format("%s: %s%n", key, value));
    // -i: in.txt
    // --limit: 40
    // -d: 1
    // -o: out.txt


Быстро и понятно. А вот для обратной задачи — сконвертировать Map с аргументами в массив строк, стримы помогут.


    String[] args = argsMap.entrySet().stream()
            .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
            .toArray(String[]::new);
    System.out.println(String.join(" ", args));
    // -i in.txt --limit 40 -d 1 -o out.txt


Дан список студентов.


    List<Student> students = Arrays.asList(
            new Student("Alex", Speciality.Physics, 1),
            new Student("Rika", Speciality.Biology, 4),
            new Student("Julia", Speciality.Biology, 2),
            new Student("Steve", Speciality.History, 4),
            new Student("Mike", Speciality.Finance, 1),
            new Student("Hinata", Speciality.Biology, 2),
            new Student("Richard", Speciality.History, 1),
            new Student("Kate", Speciality.Psychology, 2),
            new Student("Sergey", Speciality.ComputerScience, 4),
            new Student("Maximilian", Speciality.ComputerScience, 3),
            new Student("Tim", Speciality.ComputerScience, 5),
            new Student("Ann", Speciality.Psychology, 1)
            );
     
    enum Speciality {
        Biology, ComputerScience, Economics, Finance,
        History, Philosophy, Physics, Psychology
    }

У класса Student реализованы все геттеры и сеттеры, toString и equals+hashCode.

Нужно сгруппировать всех студентов по курсу.


    students.stream()
            .collect(Collectors.groupingBy(Student::getYear))
            .entrySet().forEach(System.out::println);
    // 1=[Alex: Physics 1, Mike: Finance 1, Richard: History 1, Ann: Psychology 1]
    // 2=[Julia: Biology 2, Hinata: Biology 2, Kate: Psychology 2]
    // 3=[Maximilian: ComputerScience 3]
    // 4=[Rika: Biology 4, Steve: History 4, Sergey: ComputerScience 4]
    // 5=[Tim: ComputerScience 5]


Вывести в алфавитном порядке список специальностей, на которых учатся перечисленные в списке студенты.


    students.stream()
            .map(Student::getSpeciality)
            .distinct()
            .sorted(Comparator.comparing(Enum::name))
            .forEach(System.out::println);
    // Biology
    // ComputerScience
    // Finance
    // History
    // Physics
    // Psychology


Вывести количество учащихся на каждой из специальностей.


    students.stream()
            .collect(Collectors.groupingBy(
                    Student::getSpeciality, Collectors.counting()))
            .forEach((s, count) -> System.out.println(s + ": " + count));
    // Psychology: 2
    // Physics: 1
    // ComputerScience: 3
    // Finance: 1
    // Biology: 3
    // History: 2


Сгруппировать студентов по специальностям, сохраняя алфавитный порядок специальности, а затем сгруппировать по курсу.


    Map<Speciality, Map<Integer, List<Student>>> result = students.stream()
            .sorted(Comparator
                    .comparing(Student::getSpeciality, Comparator.comparing(Enum::name))
                    .thenComparing(Student::getYear)
            )
            .collect(Collectors.groupingBy(
                    Student::getSpeciality,
                    LinkedHashMap::new,
                    Collectors.groupingBy(Student::getYear)));


Теперь это всё красиво вывести.


    result.forEach((s, map) -> {
        System.out.println("-= " + s + " =-");
        map.forEach((year, list) -> System.out.format("%d: %s%n", year, list.stream()
                .map(Student::getName)
                .sorted()
                .collect(Collectors.joining(", ")))
        );
        System.out.println();
    });


-= Biology =-
2: Hinata, Julia
4: Rika

-= ComputerScience =-
3: Maximilian
4: Sergey
5: Tim

-= Finance =-
1: Mike

-= History =-
1: Richard
4: Steve

-= Physics =-
1: Alex

-= Psychology =-
1: Ann
2: Kate

Проверить, есть ли третьекурсники среди учащихся всех специальностей кроме физики и CS.


    students.stream()
            .filter(s -> !EnumSet.of(Speciality.ComputerScience, Speciality.Physics)
                    .contains(s.getSpeciality()))
            .anyMatch(s -> s.getYear() == 3);
    // false


Вычислить число Пи методом Монте-Карло.

    final Random rnd = new Random();
    final double r = 1000.0;
    final int max = 10000000;
    long count = IntStream.range(0, max)
            .mapToObj(i -> rnd.doubles(2).map(x -> x * r).toArray())
            .parallel()
            .filter(arr -> Math.hypot(arr[0], arr[1]) <= r)
            .count();
    System.out.println(4.0 * count / max);
    // 3.1415344


Вывести таблицу умножения.


    IntStream.rangeClosed(2, 9)
            .boxed()
            .flatMap(i -> IntStream.rangeClosed(2, 9)
                    .mapToObj(j -> String.format("%d * %d = %d", i, j, i * j))
            )
            .forEach(System.out::println);
    // 2 * 2 = 4
    // 2 * 3 = 6
    // 2 * 4 = 8
    // 2 * 5 = 10
    // ...
    // 9 * 7 = 63
    // 9 * 8 = 72
    // 9 * 9 = 81


Или более экзотический вариант, в 4 столбца, как на школьных тетрадях.


    IntFunction<IntFunction<String>> function = i -> j -> String.format("%d x %2d = %2d", i, j, i * j);
    IntFunction<IntFunction<IntFunction<String>>> repeaterX = count -> i -> j ->
            IntStream.range(0, count)
                    .mapToObj(delta -> function.apply(i + delta).apply(j))
                    .collect(Collectors.joining("\t"));
    IntFunction<IntFunction<IntFunction<IntFunction<String>>>> repeaterY = countY -> countX -> i -> j ->
            IntStream.range(0, countY)
                    .mapToObj(deltaY -> repeaterX.apply(countX).apply(i).apply(j + deltaY))
                    .collect(Collectors.joining("\n"));
    IntFunction<String> row = i -> repeaterY.apply(10).apply(4).apply(i).apply(1) + "\n";
    IntStream.of(2, 6).mapToObj(row).forEach(System.out::println);
12. Задачи


    IntStream.concat(
            IntStream.range(2, ),
            IntStream.rangeClosed(, ))
        .forEach(System.out::println);
    // 2, 3, 4, 5, -1, 0, 1, 2
     
    IntStream.range(5, 30)
            .limit(12)
            .skip(3)
            .limit(6)
            .skip(2)
            .forEach(System.out::println);
    // , , ,
     
    IntStream.range(0, 10)
        .skip(2)
        .dropWhile(x -> x < )
        .limit()
        .forEach(System.out::println);
    // 5, 6, 7
     
    IntStream.range(0, 10)
        .skip()
        .takeWhile(x -> x < )
        .limit(3)
        .forEach(System.out::println);
    // 3, 4
     
    IntStream.range(1, 5)
            .flatMap(i -> IntStream.generate(() -> ).())
            .forEach(System.out::println);
    // 1, 2, 2, 3, 3, 3, 4, 4, 4, 4
     
    int x = IntStream.range(-2, 2)
            .map(i -> i * )
            .reduce(10, Integer::sum);
    // x: 0
     
    IntStream.range(0, 10)
            .boxed()
            .collect(Collectors.(i -> ))
            .entrySet().forEach(System.out::println);
    // false=[1, 3, 5, 7, 9]
    // true=[0, 2, 4, 6, 8]
     
    IntStream.range(-5, 0)
            .flatMap(i -> IntStream.of(i, ))
            .()
            .forEach(System.out::println);
    // -5, -4, -3, -2, -1, 1, 2, 3, 4, 5
     
    IntStream.range(-5, 0)
            .flatMap(i -> IntStream.of(i, ))
            .()
            .sorted(Comparator.comparing(Math::))
            .forEach(System.out::println);
    // -1, 1, -2, 2, -3, 3, -4, 4, -5, 5
     
    IntStream.range(1, 5)
            .flatMap(i -> IntStream.generate(() -> i).limit(i))
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.()))
            .entrySet().forEach(System.out::println);
    // 1=1
    // 2=2
    // 3=3
    // 4=4



13. Советы и best practices

1. Если задачу не получается красиво решить стримами, не решайте её стримами.

2. Если задачу не получается красиво решить стримами, не решайте её стримами!

3. Если задача уже красиво решена не стримами, всё работает и всех всё устраивает, не перерешивайте её стримами!

4. В большинстве случаев нет смысла сохранять стрим в переменную. Используйте цепочку вызовов методов (method chaining).
   [копировать] [скачать]

   // Нечитабельно
   Stream<Integer> stream = list.stream();
   stream = stream.filter(x -> x > 2);
   stream.forEach(System.out::println);
   // Так лучше
   list.stream()
   .filter(x -> x > 2)
   .forEach(System.out::println);


5. Старайтесь сперва отфильтровать стрим от ненужных элементов или ограничить его, а потом выполнять преобразования.
   [копировать] [скачать]

   // Лишние затраты
   list.stream()
   .sorted()
   .filter(x -> x > 0)
   .forEach(System.out::println);
   // Так лучше
   list.stream()
   .filter(x -> x > 0)
   .sorted()
   .forEach(System.out::println);


6. Не используйте параллельные стримы везде, где только можно. Затраты на разбиение элементов, обработку в другом потоке и последующее их слияние порой больше, чем выполнение в одном потоке. Читайте об этом здесь — When to use parallel streams.

7. При использовании параллельных стримов, убедитесь, что нигде нет блокирующих операций или чего-то, что может помешать обработке элементов.

   list.parallelStream()
   .filter(s -> isFileExists(hash(s)))
   ...


8. Если где-то в модели вы возвращаете копию списка или другой коллекции, то подумайте о замене на стримы. Например:
   [копировать] [скачать]

   // Было
   class Model {

        private final List<String> data;
     
        public List<String> getData() {
            return new ArrayList<>(data);
        }
   }

   // Стало
   class Model {

        private final List<String> data;
     
        public Stream<String> dataStream() {
            return data.stream();
        }
   }


Теперь есть возможность получить не только список model.dataStream().collect(toList());, но и множество, любую другую коллекцию, отфильтровать что-то, отсортировать и так далее. Оригинальный List<String> data так и останется нетронутым.
