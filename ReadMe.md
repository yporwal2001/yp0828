Assessment Demo Project
This is a springBoot using Java 21 JDK even though it should work on java 11 too.
THis project contains the source code and unit test cases for Controller and Service classes.
It uses in-memory H2 database to store and retrieve data.

I have made few assumptions 
1. If CheckoutDate/dueDate is on a holiday like July 4th (saturday or sunday) and tool is rented for weekend only. If the tool type has no charges to weekend , the rental contract is free.
2. The Assessment will return final charge as $0.0

