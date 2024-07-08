# HELP4DEVS AWS JAVA
A simple repository to serve various functions and methods made in Java

> This repository is a little project to help the Java developers on your daily needs. In this project, you can find 
> many resources that are commonly used in most of the situations during the specific needed or requirement to solve 
> any issue or problem from any case.

# Summary

The content that you will find out in this project are:

- <a href="#base">Base</a>
- <a href="#currency">Currency</a>
- <a href="#database">Database</a>
- <a href="#date">Date</a>
- <a href="#filehandler">FileHandler</a>
- <a href="#filereader">FileReader</a>
- <a href="#path">Path</a>
- <a href="#stringhandler">StringHandler</a>
- <a href="#validator">Validator</a>
- <a href="#tools">Tools</a>

> IMPORTANT NOTE:<br>
> All methods and functionalities are placed int the project scope in the path src/main/java/com/huntercodexs/demo/services
> and all tests are placed in the test scope from src/test/java/codexstester/test/unitary/Help4DevsUnitaryTests.java.
> You can follow this documentation or read and execute directly the @Test for each function afford here.

# Base

[Help4DevsBaseService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsBaseService.java)


<br>
<h4>public static void params(JSONObject... jsonObjects)</h4>
<hr>

This method offer the functionality to receive all arguments was passed as the parameters, for example:

<pre>
    public static void params(JSONObject... jsonObjects) {
        System.out.println(Arrays.toString(jsonObjects));
    }
</pre>

<pre>
    @Test
    public void paramsTests() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }
</pre>

In this case we have the following result

<pre>
[{"name":"Test 1"}, {"name":"Test 2"}]
</pre>

Look at the above result and see that there two objects of type JSON, and they were passed to the method in the separated 
way, however the method have a single expected argument (JSONObject... jsonObjects) that automatically convert these 
parameters to a list parameters with a type define in the signature.

<br>
<h4>public static String numberFormatter(int input, String format)</h4>
<hr>

The method numberFormatter is shortcut to format a number in the specific way. Maybe you need format one number from 
left to right filling using a specific number. Below is the common use to this functionality:

<pre>
    public static String numberFormatter(int input, String format) {
        return String.format(format, input);
    }
</pre>

<pre>
    @Test
    public void numberFormatterTest() {
        String result = numberFormatter(1, "%09d");
        System.out.println(result);
    }
</pre>

As a result we have the following output

<pre>
000000001
</pre>

<br>
<h4>public static String stringFormatter(String input, String format)</h4>
<hr>

The following method afford the function that format one string in a specific way. Below are the method mentioned in 
this topic, look at it how simple is the implementation

<pre>
    public static String stringFormatter(String input, String format) {
        return String.format(format, input);
    }
</pre>

<pre>
    @Test
    public void stringFormatterTest() {
        String result1 = stringFormatter("XXX", "%10s");
        System.out.println("["+result1+"]");

        String result2 = stringFormatter("XXX", "%-10s");
        System.out.println("["+result2+"]");
    }
</pre>

In this case, we are formatting one string XXX with 10 spaces from the left to right and also from the right to left. It 
can be seen when the second parameter is set using a character "-" in front of the number quantity formatter. The result 
for both cases above are showed below

Using %10s

<pre>
[       XXX]
</pre>

Using %-10s

<pre>
[XXX       ]
</pre>

<br>
<h4>public static String fillerFormatter(String input, String filler, String align, int size)</h4>
<hr>

This functionality offer a better way to format one string with a specific char, that can be a number or a string, and 
also can be made from left to right and right to left, below we have one simple example to explain in the better way 
what we're talking about, let's look at it. 

<pre>
    public static String fillerFormatter(String input, String fill, String align, int size) {

        if (!align.equals("left") && !align.equals("right")) {
            System.out.println("Error: use left or right to param [align]");
            return null;
        }

        if (size < 0) {
            System.out.println("Error: use size > 0");
            return null;
        }

        String formatted = input;

        int lenValue = input.length();
        int lenFill = size - lenValue;
        String repeat = fill.repeat(lenFill);

        if (align.equals("left")) {
            formatted = input + repeat;
        } else {
            formatted = repeat + input;
        }

        return formatted;
    }
</pre>

<pre>
    @Test
    public void fillerFormatterTest() {
        String result1 = fillerFormatter("XXX", "F", "left", 20);
        System.out.println("["+result1+"]");

        String result2 = fillerFormatter("XXX", "F", "right", 20);
        System.out.println("["+result2+"]");

        String result3 = fillerFormatter("ZZZ", "8", "left", 20);
        System.out.println("["+result3+"]");

        String result4 = fillerFormatter("ZZZ", "8", "right", 20);
        System.out.println("["+result4+"]");

        String result5 = fillerFormatter("YYY", "A", "left", -20);
        System.out.println("["+result5+"]");

        String result6 = fillerFormatter("YYY", "A", "right", -20);
        System.out.println("["+result6+"]");
    }
</pre>

The result will be something like below

<pre>
[XXXFFFFFFFFFFFFFFFFF]
[FFFFFFFFFFFFFFFFFXXX]
[ZZZ88888888888888888]
[88888888888888888ZZZ]
Error: use size > 0
[null]
Error: use size > 0
[null]
</pre>

<br>
<h4>public static String rgFormatter(String value, String rgUf)</h4>
<hr>

This method is only used in the Brazil, because it serves to specific situation in that country, where we need to manager 
or formatter the document number or document type from anyone in the system. So, maybe we need to add any information 
or remove the information from document (RG), in these case we can use this method in the way showed below

<pre>
    public static String rgFormatter(String value, String rgUf) {
        if (value == null || value.equals("")) return "";
        if (rgUf == null) rgUf = "";
        if (!rgUf.contains("SSP") && !rgUf.equals("")) return "";

        //SSP CP = SSPSC, SSP/SP = SSPSP
        rgUf = rgUf.replaceAll("[^A-Z]+", "");

        if (rgUf.equals("SSPSP") || rgUf.equals("SP")) {
            rgUf = "";
        } else {
            //SSPCRJ = RJ, SSPSC = SC
            rgUf = rgUf.replaceAll("SSP", "");
        }

        return "RG"+value.replaceAll("[^0-9]+", "")+rgUf;
    }
</pre>

<pre>
    @Test
    public void rgFormatterTest() {
        System.out.println(" > ["+ rgFormatter("9090909090", "CNH")+"]");
        System.out.println(" > ["+ rgFormatter("7878787878", "SSP SC")+"]");
        System.out.println(" > ["+ rgFormatter("6767676767", "SSPSP")+"]");
        System.out.println(" > ["+ rgFormatter("1212121212", "SSCMG")+"]");
        System.out.println(" > ["+ rgFormatter("2020202020", "SSP/RJ")+"]");
    }
</pre>

The result will be something like the below

<pre>
 > []
 > [RG7878787878SC]
 > [RG6767676767]
 > []
 > [RG2020202020RJ]
</pre>

Look that this method manager the information and makes a few validations to format the documentation identification RG 
in the form correct form for each place in the Brazil, and not accept empty rgUf. 

# Currency

[Help4DevsCurrencyService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsCurrencyService.java)

<br>
<h4>public static String brCurrency(float value)</h4>
<hr>

This method only works with Reais currency that are used in Brazil. To use it, just pass one parameter value in float 
format, below we can see an example using this method.

<pre>
    public static String brCurrency(float value) {
        if (value <= 0) return "";
        Locale localBrazil = new Locale("pt", "BR");
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(localBrazil);
        return brCurrency.format(value)
                .replaceAll("[^0-9R$., ]+", "")
                .replaceAll("R[$]", "R\\$ ");
    }
</pre>

<pre>
    @Test
    public void brCurrencyFloatTest() {
        System.out.println(brCurrency(Float.parseFloat("999111111111.00")));
    }
</pre>

Result

<pre>
R$ 999.111.131.136,00
</pre>

<br>
<h4>public static String brCurrency(double value)</h4>
<hr>

In this case we have the same idea or purpose that was used in the previous method above. However, in this case we need 
to pay attention in the type of parameter, that should be double, not float. Below it's possible see the correct use 
fo this method and the result

<pre>
    public static String brCurrency(double value) {
        if (value <= 0) value = 0.00;
        Locale localBrazil = new Locale("pt", "BR");
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(localBrazil);
        return brCurrency.format(value)
                .replaceAll("[^0-9R$., ]+", "")
                .replaceAll("R[$]", "R\\$ ");
    }
</pre>

<pre>
    @Test
    public void brCurrencyDoubleTest() {
        System.out.println(brCurrency(999111111111.00));
    }
</pre>

Result

<pre>
R$ 999.111.111.111,00
</pre>

<br>
<h4>public static double currencySum(double current, double add)</h4>
<hr>

<pre>
    public static double currencySum(double current, double add) {
        System.out.println(brCurrency(current) +"+"+ brCurrency(add));
        double sum = current + add;
        System.out.println(brCurrency((float) sum));
        return sum;
    }
</pre>

<pre>
    @Test
    public void currencySumTest() {

        double result = currencySum(0.00, 0.01);
        result += currencySum(0.01, 0.10);
        result += currencySum(0.10, 0.11);
        result += currencySum(0.11, 1.11);
        result += currencySum(1.00, 1.01);
        result += currencySum(1.00, 1.10);
        result += currencySum(11.00, 111.10);
        result += currencySum(1111.00, 11.10);
        result += currencySum(111111.00, 111.10);
        result += currencySum(111.00, 11.01);
        result += currencySum(111111111.00, 11.01);
        result += currencySum(999111111111.00, 11.01);

        System.out.println("Total");
        System.out.println(brCurrency(result));

        /*Proof*/
        double proff = currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }
</pre>

Result

<pre>
R$ 0,00+R$ 0,01
R$ 0,01
R$ 0,01+R$ 0,10
R$ 0,11
R$ 0,10+R$ 0,11
R$ 0,21
R$ 0,11+R$ 1,11
R$ 1,22
R$ 1,00+R$ 1,01
R$ 2,01
R$ 1,00+R$ 1,10
R$ 2,10
R$ 11,00+R$ 111,10
R$ 122,10
R$ 1.111,00+R$ 11,10
R$ 1.122,10
R$ 111.111,00+R$ 111,10
R$ 111.222,10
R$ 111,00+R$ 11,01
R$ 122,01
R$ 111.111.111,00+R$ 11,01
R$ 111.111.120,00
R$ 999.111.111.111,00+R$ 11,01
R$ 999.111.131.136,00
Total
R$ 999.222.334.837,99
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
Total
R$ 6,00
</pre>

<br>
<h4>public static double currencySumFromString(String current, String add)</h4>
<hr>

In the same way, that was presented in the method above, we can make a sum of the number that are in string format. Just 
pay attention when the parameter are passed in the request to guarantee that those are from string type, for example:

<pre>
    public static double currencySumFromString(String current, String add) {
        System.out.println(brCurrency(Double.parseDouble(current)) +"+"+ brCurrency(Double.parseDouble(add)));
        double sum = Double.parseDouble(current) + Double.parseDouble(add);
        System.out.println(brCurrency((float) sum));
        return sum;
    }
</pre>

<pre>
    public void currencySumFromStringTest() {

        /*Proof*/
        double proff = currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }
</pre>

Result, see that even the params was in the string type, this method can be handling that situation

<pre>
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
R$ 0,00+R$ 1,00
R$ 1,00
Total
R$ 6,00
</pre>

# Database

[Help4DevsDatabaseService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsDatabaseService.java)

# Date

[Help4DevsDateService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsDateService.java)

<br>
<h4>public static String reverseDate(String inputDate, String separator)</h4>
<hr>

Whether you need make one date revert in the position of each part from any date, this is the correct and useful method.
Check the algorithm from this method inside the Help4DevsDateService.java file.

To use this method, you can follow the example below

<pre>
    @Test
    public void reverseDateTest() {
        System.out.println(reverseDate("14/07/2023 14:53:25", "-"));
        System.out.println(reverseDate("2023-08-16 16:10:28", "/"));
        System.out.println(reverseDate("14/07/2023 14:53:25", "/"));
        System.out.println(reverseDate("2023-08-16 16:10:28", "-"));

        System.out.println(reverseDate("14/07/2023TZ14:53:25", "-"));
        System.out.println(reverseDate("2023-08-16TZ16:10:28", "/"));
        System.out.println(reverseDate("14/07/2023TZ14:53:25", "/"));
        System.out.println(reverseDate("2023-08-16TZ16:10:28", "-"));
        System.out.println(reverseDate("2023-08-15T18:02:26.737Z", "-"));
        System.out.println(reverseDate("2023-08-15T18:02:26.737Z", "/"));

        System.out.println(reverseDate("14/07/2023", "-"));
        System.out.println(reverseDate("2023-08-16", "/"));
        System.out.println(reverseDate("14/07/2023", "/"));
        System.out.println(reverseDate("2023-08-16", "-"));
    }
</pre>

The expected result is something like that

<pre>
2023-07-14 14:53:25
16/08/2023 16:10:28
2023/07/14 14:53:25
16-08-2023 16:10:28
2023-07-14 14:53:25
16/08/2023 16:10:28
2023/07/14 14:53:25
16-08-2023 16:10:28
15-08-2023 18:02:26.737
15/08/2023 18:02:26.737
2023-07-14
16/08/2023
2023/07/14
16-08-2023
</pre>

<br>
<h4>public static boolean expiredDate(String date, int time, String metricType)</h4>
<hr>

To check if any date is outdated, due or any other case, use the method expiredDate, for example:

<pre>
    @Test
    public void expiredDateTest() {
        boolean time = expiredDate("14/07/2023 14:53:25", 1, "nano");
        System.out.println("RESULT IS [NANO]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "second");
        System.out.println("RESULT IS [SECOND]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "minute");
        System.out.println("RESULT IS [MINUTE]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "hour");
        System.out.println("RESULT IS [HOUR]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "day");
        System.out.println("RESULT IS [DAY]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "week");
        System.out.println("RESULT IS [WEEK]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "month");
        System.out.println("RESULT IS [MONTH]: " + time);

        time = expiredDate("14/07/2023 14:53:25", 1, "year");
        System.out.println("RESULT IS [YEAR]: " + time);
    }
</pre>

In this case we have the following result

<pre>
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.026  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T14:53:25.000000001
RESULT IS [NANO]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.026  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T14:53:26
RESULT IS [SECOND]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.027  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T14:54:25
RESULT IS [MINUTE]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.027  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-14T15:53:25
RESULT IS [HOUR]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.028  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-15T14:53:25
RESULT IS [DAY]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.028  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-07-21T14:53:25
RESULT IS [WEEK]: true
MATCH 1: 14/07/2023 14:53:25
2024-02-09 15:01:35.029  INFO jereelton-acer-nitro --- [           main] c.h.d.s.Help4DevsDateService             : Expired time: (now)2024-02-09T15:01:35 - (limit)2023-08-14T14:53:25
RESULT IS [MONTH]: true
MATCH 1: 14/07/2023 14:53:25
RESULT IS [YEAR]: false
</pre>

<br>
<h4>public static List<Long> quantifyDate(String initialDate, String finalDate)</h4>
<hr>

With this method you can quantify one time in years, months, days, hours, minutes, seconds, milliseconds to get full 
information about one specific time. The result will be something like below

<pre>
    public static List&lt;Long&gt; quantifyDate(String initialDate, String finalDate) {
        ...
    }
</pre>

<pre>
    @Test
    public void quantifyDateTest() {
        quantifyDate("14/07/2023 15:53:25", "14/07/2023 15:53:26");
    }
</pre>

<pre>
MATCH 1: 14/07/2023 15:53:25
MATCH 1: 14/07/2023 15:53:26
[0, 0, 0, 0, 0, 1, 0]
RESULT: 0 years, 0 months, 0 days, 0 hours, 0 minutes, 1 seconds, 0 milliseconds
</pre>

<br>
<h4>public static long quantifyMillisDate()</h4>
<hr>

If you need to know how log time took one specific operation or transaction, and you have 
the initial time and final time, just use this method and get the result quickly and easily

<pre>
    public static long quantifyMillisDate(long startDate, long endDate) {
        long duration = endDate - startDate;
        System.out.println("StartDate: " + startDate);
        System.out.println("EndDate: " + endDate);
        return duration;
    }
</pre>

<pre>
    @Test
    public void quantifyMillisDateTest() {
        long startDate = Calendar.getInstance().getTimeInMillis();
        try {
            Thread.sleep(3200);
            long endDate = Calendar.getInstance().getTimeInMillis();
            long duration = quantifyMillisDate(startDate, endDate);
            System.out.println("Duration: " + duration + " milliseconds");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
</pre>

The result should be something like below

<pre>
StartDate: 1707675336041
EndDate: 1707675339242
Duration: 3201 milliseconds
</pre>

<br>
<h4>public static long quantifyMillisParamsDate(String start, String end)</h4>
<hr>

In this case, if you want to know how long time took one operation or transaction, and you have a 
start time and final time, as showed below, so you can use this method to get in milliseconds the time from the transaction.

<pre>
    public static long quantifyMillisParamsDate(String start, String end) {
        ...
    }
</pre>

<pre>
    @Test
    public void quantifyMillisParamsDateTest() {
        quantifyMillisParamsDate("2023/08/20 15:30:10.100", "2023/08/20 15:31:10.500");
        quantifyMillisParamsDate("2023/08/20 15:30:10", "2023/08/20 15:31:10");
        quantifyMillisParamsDate("2023/08/20 15:30", "2023/08/20 15:31");
        quantifyMillisParamsDate("2023/08/20 15", "2023/08/20 16");
        quantifyMillisParamsDate("2023/08/20", "2023/08/21");

        quantifyMillisParamsDate("2023-08-20 15:30:10.100", "2023-08-20 15:30:10.500");
        quantifyMillisParamsDate("2023-08-20 15:30:10", "2023-08-20 15:31:10");
        quantifyMillisParamsDate("2023-08-20 15:30", "2023-08-20 15:31");
        quantifyMillisParamsDate("2023-08-20 15", "2023-08-20 16");
        quantifyMillisParamsDate("2023-08-20", "2023-08-21");
    }
</pre>

The result look like as below

<pre>
DURATION: 60400
DURATION: 60000
DURATION: 60000
DURATION: 3600000
DURATION: 86400000
DURATION: 400
DURATION: 60000
DURATION: 60000
DURATION: 3600000
DURATION: 86400000
</pre>

<br>
<h4>public static String localDateFromGmtDate(String gmtDate, String operation, int time)</h4>
<hr>

We can use this method to get correctly datetime from API standards when using GMT. For example, if you are in the Brazil 
and receive one API response form any service, and it is using the GMT system date, you need make datetime minus 3 hours, 
like this: 20/10/2020 13:00:00 to 20/10/2020 10:00:00.

The code below show with more details and clear how to this work

<pre>
    public static String localDateFromGmtDate(String gmtDate, String operation, int time) {

        if (!gmtDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})Z")) {
            return "invalid date format";
        }

        DateTimeFormatter formatterDash = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        DateTimeFormatter formatterBar  = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        String[] saveMillis = gmtDate.replaceAll("Z", "").split("\\.");

        gmtDate = gmtDate
                .replaceAll("[TZ]", " ")
                .trim()
                .replaceAll("\\.[0-9]+$", "");

        LocalDateTime dateTimeRef;

        try {
            dateTimeRef = LocalDateTime.parse(gmtDate, formatterDash);
        } catch (DateTimeParseException re) {
            dateTimeRef = LocalDateTime.parse(gmtDate, formatterBar);
            formatterDate = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        }

        if (operation.equals("-")) {
            String dt = dateTimeRef.minusHours(time).toLocalDate().format(formatterDate);
            LocalTime tm = dateTimeRef.minusHours(time).toLocalTime();
            return dt + " " + tm + "." + saveMillis[1];

        } else if (operation.equals("+")) {
            String dt = dateTimeRef.plusHours(time).toLocalDate().format(formatterDate);
            LocalTime tm = dateTimeRef.plusHours(time).toLocalTime();
            return dt + " " + tm + "." + saveMillis[1];

        } else {
            throw new RuntimeException("Invalid option to localDateFromGmtDate, use: - or +");
        }
    }
</pre>

<pre>
    @Test
    public void convertToLocalDateTest() {
        String localDate = localDateFromGmtDate("2023-08-15T02:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("2023-08-14 23:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15T18:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("2023-08-15 15:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023/08/15T18:02:26.737Z", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("2023/08/15 15:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15T18:02:26.737Z", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertText("2023-08-15 21:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023/08/15T18:02:26.737Z", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertText("2023/08/15 21:02:26.737", localDate);

        localDate = localDateFromGmtDate("2023-08-15 18:02:26", "-", 3);
        System.out.println("RESULT IS [MINUS]: " + localDate);
        codexsTesterAssertText("invalid date format", localDate);

        localDate = localDateFromGmtDate("2023-08-15 18:02:26", "+", 3);
        System.out.println("RESULT IS [PLUS]: " + localDate);
        codexsTesterAssertText("invalid date format", localDate);
    }
</pre>

The result should be like something like below

<pre>
RESULT IS [MINUS]: 2023-08-14 23:02:26.737
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS [MINUS]: 2023-08-15 15:02:26.737
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS [MINUS]: 2023/08/15 15:02:26.737
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS [PLUS]: 2023-08-15 21:02:26.737
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS [PLUS]: 2023/08/15 21:02:26.737
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS [MINUS]: invalid date format
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS [PLUS]: invalid date format
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
</pre>

# FileHandler

[Help4DevsFileHandlerService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsFileHandlerService.java)

<br>
<h4>public static Properties loadProps(String classpath)</h4>
<hr>

You probably already needed to get data from the application.properties, and maybe you stayed in the look-up in the internet 
seeking that information. Even though it's can very simple, beginners can face a little challenge to do it, so here is 
the simple method to get it done.

<pre>
    public static Properties loadProps(String classpath) {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(classpath);
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }
</pre>

<pre>
    @Test
    public void loadPropsTest() {
        Properties props = loadProps("classpath:application.properties");
        System.out.println(props);
    }
</pre>

The Result should be something like below

<pre>
{application-version=23.01.1-SNAPSHOT, info.version=23.01.1-SNAPSHOT, txt.filepath=/home/jereelton/txt/, server.port=35000, application-description=@project.description@, logging.config=src/main/resources/log4j2.xml, txt.filename=spring-batch-job-demo-data.txt}
</pre>

> NOTE:<br>
> The methods below work gather and each one is a complement for each one 

<br>
<h4>public static InputStream bytesFileExtractor(String targetPath, String targetFile)</h4>
<hr>

<br>
<h4>public static InputStream fileToByteArray(String targetPath, String targetFile)</h4>
<hr>

<br>
<h4>public static ByteArrayDataSource fileToDataSource(String targetPath, String targetFile) throws IOException</h4>
<hr>

<br>
<h4>public static byte[] byteConvert(InputStream fileArray) throws IOException</h4>
<hr>

<br>
<h4>public static String fileToString(String targetPath, String targetFile)</h4>
<hr>

With this method you can revert the transformation made by others methods, for example fileToDataSource, in a readable 
and human format content, simply the string format

<br>
<h4>public static ArrayList<String> fileToArray(String targetPath, String targetFile)</h4>
<hr>

# FileReader

[Help4DevsFileReaderService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsFileReaderService.java)

> NOTE:<br>
> The methods below are a package to work like an assistant to method getFileContentByMatch, however, you can use 
> each one from these methods alone to get the solution for your needed

<br>
<h4>public static FileReader open(String filepath)</h4>
<hr>

Open a specific file to read

<br>
<h4>public static BufferedReader buffer(FileReader activateFile)</h4>
<hr>

Create the buffer from one opened file and save data temporarily in it

<br>
<h4>public static String reader(BufferedReader readActivateFile)</h4>
<hr>

Reader the buffer created in the buffer method to get data that was saved in the memory

<br>
<h4>public static public static void close(FileReader activateFile)</h4>
<hr>

Close one opened file

<br>
<h4>public static String getFileContentByMatch(String filepath, String regex, int timeout)</h4>
<hr>

This method make a constantly reading from a specific source file and get the content according the regex passed in the 
parameter, for example:

<pre>
    public static String getFileContentByMatch(String filepath, String regex, int timeout) throws Exception {
        ...
        return content;
    }
</pre>

<pre>
    @Test
    public void getFileContentTest() throws Exception {
        /*TIP: Edit the file ./src/test/resources/help4devs/file.txt and press [Ctrl+S] button*/
        String code = getFileContentByMatch("./src/test/resources/help4devs/file.txt", "[0-9]{6}",1500000000);
        System.out.println("Content: " + code);
    }
</pre>

The result probably will be something like below

<pre>
    ------------------------------------------------------------------------------------------------------------------------
    [INFO] >> Opening file: ./src/test/resources/help4devs/file.txt
    [INFO] << Closing file: ./src/test/resources/help4devs/file.txt
    ------------------------------------------------------------------------------------------------------------------------
    [INFO] >> Opening file: ./src/test/resources/help4devs/file.txt
    Content: 898989
</pre>

# Path

[Help4DevsPathService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsPathService.java)

<br>
<h4>public static String sanitizePath(String path)</h4>
<hr>

This method simply make a clean in the specified path like showed below

<pre>
    public static String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }
</pre>

<pre>
    @Test
    public void sanitizePathTest() {
        String result = sanitizePath("/home/user/test/");
        System.out.println("RESULT IS: " + result);

        result = sanitizePath("/home/user/test");
        System.out.println("RESULT IS: " + result);
    }
</pre>

The result should be something like that

<pre>
RESULT IS: /home/user/test/
RESULT IS: /home/user/test/
</pre>

<br>
<h4>public static String sanitizeAscii(String input)</h4>
<hr>

Use this method to remove special characters that can broken your database or the correctly words format.
This is a useful method when you don't know about the data source from your customers or services, and you need 
guarantee the perfect form for the words in the phrases.

<pre>
    public static String sanitizeAscii(String input, String letterType) {
        if (letterType == null) letterType = "";
        try {
            if (letterType.endsWith("upper")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
            } else if (letterType.endsWith("lower")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            } else {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            }
        } catch (RuntimeException re) {
            log.error("Normalize Error: " + re.getMessage());
            throw new RuntimeException(re.getMessage());
        }
    }
</pre>

<pre>
    @Test
    public void sanitizeAsciiTest() {
        String result = sanitizeAscii("Teste com acentuação é inevital !", "upper");
        System.out.println("RESULT IS: " + result);

        result = sanitizeAscii("Teste com acentuação é inevital !", "lower");
        System.out.println("RESULT IS: " + result);

        result = sanitizeAscii("Teste com acentuação é inevital !", null);
        System.out.println("RESULT IS: " + result);
    }
</pre>

Below we can see a result

<pre>
RESULT IS: TESTE COM ACENTUACAO E INEVITAL !
RESULT IS: teste com acentuacao e inevital !
RESULT IS: Teste com acentuacao e inevital !
</pre>

# StringHandler

[Help4DevsStringHandlerService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsStringHandlerService.java)

<br>
<h4>public static String queryStringBuilder(Object input)</h4>
<hr>

If you have any string like showed below, be on JSON format or any other format, you can need transform this data to one 
specific type of string before send by webclient or any request to any API, maybe this format should be a query string, 
so you can use this method to do it.

<pre>
    public static String queryStringBuilder(Object input) {
        return input.toString().split("},")[0]
                .replaceAll("[]}{\\[\"']", "")
                .replaceAll(", ", "&")
                .replaceAll(",", "&")
                .replaceAll(":", "=")
                .replaceAll("= ", "=");
    }
</pre>

<pre>
    @Test
    public void queryStringBuilderTest() {
        String result = queryStringBuilder("[{age: 40, gender: female},{age: 30, gender: female}]");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("[{age: 40, gender: female}]");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{age: 40, gender: female}");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{'age': '40', 'gender': 'female'}");
        System.out.println("RESULT IS: " + result);

        result = queryStringBuilder("{\"age\": \"40\", \"gender\": \"female\"}");
        System.out.println("RESULT IS: " + result);
    }
</pre>

The result should be something like that

<pre>
RESULT IS: age=40&gender=female
RESULT IS: age=40&gender=female
RESULT IS: age=40&gender=female
RESULT IS: age=40&gender=female
RESULT IS: age=40&gender=female
</pre>

<br>
<h4>public static String getDataFromQueryString(String queryString, String field)</h4>
<hr>

In this case, whether you need to get one specific data from one query string, so just use this method, and make your 
life easier.

<pre>
    public static String getDataFromQueryString(String queryString, String field) {

        String str = (queryString.split(field+"=")[1]);

        if (str.contains("&")) {
            str = str.split("&")[0];
        }

        return str;
    }
</pre>

<pre>
    @Test
    public void getDataFromQueryStringTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        String result = getDataFromQueryString(queryString, "age");
        System.out.println("RESULT IS: " + result);
    }
</pre>

The result can be seen below

<pre>
RESULT IS: 40
</pre>

<br>
<h4>public static JSONObject queryStringToJson(String input)</h4>
<hr>

With this method it's possible to convert one query string to json string format, look the example below

<pre>
    public static JSONObject queryStringToJson(String input) {

        System.out.println("INPUT STRING: " + input);

        String[] splitter = input.split("&");
        JSONObject jsonData = new JSONObject();

        for (String split : splitter) {
            String[] splitter2 = split.split("=");
            jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
        }

        return jsonData;

    }
</pre>

<pre>
    @Test
    public void queryStringToJsonTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        JSONObject result = queryStringToJson(queryString);
        System.out.println("RESULT IS: " + result);
    }
</pre>

The result

<pre>
INPUT STRING: age=40&gender=female
RESULT IS: {"gender":"female","age":"40"}
</pre>

<br>
<h4>public static JSONObject stringToJson(String str)</h4>
<hr>

The similar method to queryStringToJson, the stringToJson can convert one string to json format

<pre>
    public static JSONObject stringToJson(String str) {

        JSONObject jsonData = new JSONObject();
        String strClean = str.replaceAll("([\"{\\[\\]}'/\\\\]+)", "");

        try {
            String[] splitter = strClean.split(",");

            for (String split : splitter) {
                String[] splitter2 = split.split(":");
                jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
            }
        } catch (Exception e) {
            try {
                String[] splitter = strClean.split(":");
                jsonData.appendField(splitter[0].trim(), splitter[1].trim());
            } catch (Exception er) {
                jsonData.appendField("message", null);
            }
        }

        return jsonData;
    }
</pre>

<pre>
    @Test
    public void stringToJsonTest() {
        JSONObject result = stringToJson("{\"age\": \"40\", \"gender\": \"female\"}");
        System.out.println("RESULT IS: " + result);
    }
</pre>

Result

<pre>
RESULT IS: {"gender":"female","age":"40"}
</pre>

# Validator

[Help4DevsValidatorService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsValidatorService.java)

<br>
<h4>public static boolean cpfValidator(String cpf)</h4>
<hr>

To validate a CPF document (in Brazil)

<pre>
    public static boolean cpfValidator(String cpf) {
        ...
    }
</pre>

<pre>
    @Test
    public void cpfValidatorTest() {
        boolean result = cpfValidator("07365238801");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);

        boolean result2 = cpfValidator("07365238899");
        System.out.println("RESULT IS: " + result2);
        codexsTesterAssertBool(result2, false);
    }
</pre>

The result should be something like that

<pre>
RESULT IS: true
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS: false
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
</pre>

<br>
<h4>public static boolean mailValidator(String email)</h4>
<hr>

To validate email address

<pre>
    public static boolean mailValidator(String email) {
        ...
    }
</pre>

<pre>
    @Test
    public void mailValidatorTest() throws Exception {
        boolean result = mailValidator("marcos_portela@yahoo.com.br");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);

        result = mailValidator("johnsmith23@email.com");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);
    }
</pre>

The result is showed below

<pre>
RESULT IS: true
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS: true
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
</pre>

<br>
<h4>public static boolean phoneValidator(String phoneNumber)</h4>
<hr>

To validate a phone number

<pre>
    public static boolean phoneValidator(String phoneNumber, String country) {
        boolean isValidPhone = false;

        String expression = phoneExpression(country);

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        return isValidPhone;
    }
</pre>

<pre>
    @Test
    public void phoneValidatorTest() {
        boolean result = phoneValidator("5511982772389", "br");
        System.out.println("RESULT IS: " + result);
        codexsTesterAssertBool(result, true);

        boolean result2 = phoneValidator("5511982772", "br");
        System.out.println("RESULT IS: " + result2);
        codexsTesterAssertBool(result2, false);

        boolean result3 = phoneValidator("551187722212", "br");
        System.out.println("RESULT IS: " + result3);
        codexsTesterAssertBool(result3, true);
    }
</pre>

<pre>
RESULT IS: true
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS: false
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
RESULT IS: true
------------------------------------------------------------------------------------------------------------------------
CODEXS TESTER FINISHED: PASSED
</pre>

# Tools

[Help4DevsToolsService.java](src/main/java/com/huntercodexs/demo/services/Help4DevsToolsService.java)

<br>
<h4>public static String md5(String data)</h4>
<hr>

Generate a md5sum dynamically and randomly

<pre>
    public static String md5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }
</pre>

<br>
<h4>public static String guide(String tcn)</h4>
<hr>

Generate a GUID randomly

<pre>
    public static String guide(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }
</pre>
