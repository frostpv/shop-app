### Universal online Store

In this project:
<ul>
<li> User registration</li>
<li> User / admin roles</li>
<li> User cart</li>
<li> Adding products to the store</li>
<li> Removing products from the store</li>
<li>User order creation</li>
<li>User authorization and authentication</li>
<li> User filtering by RBAC</li>
<li>managing users;
<li>view orders and manage it</li>
</ul>

#### **Technologies Used**
<ul>
<li>Java 8</li>
<li>Maven 3.2.3</li>
<li>Maven Checkstyle Plugin</li>
<li>Apache Tomcat</li>
<li>Mysql Connector Java 8.0.21</li>
<li>JSTL 1.2</li>
<li>JSP</li>
</ul>

##### Project launching

To run the project on your local machine, clone this repository. MySQL, Tomcat Server, and Java must be installed on your machine.

Initialize the database (example here -> init_db.sql). Use MySQL workbench or any other application.

During the first launch, follow the link: http://your-domain.com:8080/inject. Test users Admin and Laci will be automatically created with passwords: 123.
Don't forget to specify the database access in the file: src / main / java / com / internet / shop / util / ConnectionUtil.java

Author: https://github.com/frostpv


