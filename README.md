# Miguel Lopes

Challenge questions can be found in Exercise.md

Java program compatible with any version >=5. No external libraries are required.

I delivered a .zip file. After unzipping, the executable .jar file can be found under ./target/totalCallsCost.jar. All the source files can be found in the same structure as the Standard Directory Layout of Apache Maven Project.

## Usage

```console
miguel@test:~$ java -jar totalCallsCost.jar <input_file>
```

To help the user, it is possible to use the following command to check how the program should be used, as well as the available optional parameters:
```console
miguel@test:~$ java -jar totalCallsCost.jar help
usage: totalCallsCost <input_file> [<optional_args>]
<input_file> should have multiple lines with the following format: <time_of_start>;<time_of_finish>;<call_from>;<call_to>
<time_of_start> and <time_of_finish> should be in the format HH:mm:ss
Example: 09:11:30;09:15:22;+351914374373;+351215355312
Available <optional_args>: [-printAllCalls, -printChargedCalls]
```

For instance, with the following input file:
```console
miguel@test:~$ cat test.txt
09:11:30;09:15:22;+351914374373;+351215355312
15:20:04;15:23:49;+351217538222;+351214434422
16:43:02;16:50:20;+351217235554;+351329932233
17:44:04;17:49:30;+351914374373;+351963433432
```

Example1:
```console
miguel@test:~$ java -jar totalCallsCost.jar test.txt
48,35
```

Example2:
```console
miguel@test:~$ java -jar totalCallsCost.jar test.txt -printAllCalls -printChargedCalls
48,35
--------- All Calls ---------
Duration(min)	Cost(Cents)
+351217235554
7,300			29,60
+351914374373
3,867			19,34
5,433			25,87
+351217538222
3,750			18,75
--------- Charged Calls ---------
Duration(min)	Cost(Cents)
+351217235554
7,300			29,60
+351217538222
3,750			18,75
```

## Basic Architecture

There are 4 **Model classes**, independent of the input:
* Call - Object Call
* Caller - A List<Call>
* Callers - A Map<PhoneNumber, Caller>
* PhoneNumber - Object PhoneNumber


There is a **Parser class**, responsible for interacting between the input and Model classes.

There is an **App class** that runs the program. There is also an extra Config class, with essentially simple, static constants.

I also created custom **exceptions** for possible input errors.

Lastly, I created a series of **unit tests**.

I used **Maven** to build, test and package the program:
```console
miguel@test:~$ mvn clean compile test assembly:single
```

## Other notes

* Since i didn't know which java version I should use, I made sure the program was compatible with Java 5+. I also didn't (purposely) use any external library.

* I assumed that I could charge fractions of a minute, since the smallest time unit in the input is the second. So a call with 1.15min would be charged at 5*1.15 cents.

* Assumed the following for exceptions:
  * If a file isn't given, an exception is thrown.
  * If the file is empty, an exception is thrown.
  * If each line in the given file is not in the form <time_of_start>;<time_of_finish>;<call_from>;<call_to>, with <time_of_start> and <time_of_finish> in the format HH:mm:ss, an exception is thrown.
  * If the times are chronologically wrong, an exception is thrown.

* I created some simple optional arguments that give more insightful information about the calls.

* If two or more callers have the same total call duration, only one of them is chosen to not be charged.

* I had to use BigDecimal to calculate the cost, since double isn't precise enough.

* I created a PhoneNumber class for future proof (for instance, maybe we will want the country code), although I could have just used a string. I also didn't validate the phone numbers (it was not needed for this exercise and I'd have to study the rules on the various possible numbers), or if calls were being received simultaneously by the same number, but this class could also be useful to, in the future, validate these cases.
