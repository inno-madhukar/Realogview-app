# Realogview-app

# how to run RealogView code

 1. download jdk 20 or later version.

 2. open source code in intellij Idea.(with root folder RealogView-app) 

 3. maven tool download require library automaticaly from pom.xml file.

 4. Set SDK path in project. (follow further setps)
 file > project structure > project setting > project
in project section click on "Edit" to set JDK path.

 5. then click on run button.

# email id,password change:

path : src/main/java/com/example/dmmguivisible/DMContiguity.java

Email id (at line 82 in DMContiguity.java file)

Email password (at line 83 in DMContiguity.java file)


# custome jre modules list :

JAVA_VERSION="20.0.1"
MODULES="java.base java.datatransfer java.xml java.prefs java.desktop java.instrument java.logging java.management java.security.sasl java.naming java.rmi java.management.rmi java.net.http java.scripting java.security.jgss java.transaction.xa java.sql java.xml.crypto jdk.charsets jdk.crypto.ec jdk.crypto.cryptoki jdk.crypto.mscapi jdk.hotspot.agent jdk.httpserver jdk.internal.jvmstat jdk.jstatd jdk.net jdk.sctp jdk.security.auth jdk.security.jgss jdk.unsupported"

# deleted modules list: 

MODULES=" java.compiler java.sql.rowset java.se java.smartcardio jdk.accessibility jdk.attach jdk.zipfs jdk.compiler jdk.dynalink jdk.internal.ed jdk.editpad jdk.incubator.concurrent jdk.incubator.vector jdk.internal.le jdk.internal.opt jdk.internal.vm.ci jdk.internal.vm.compiler jdk.internal.vm.compiler.management jdk.jartool jdk.javadoc jdk.jcmd jdk.management jdk.management.agent jdk.jconsole jdk.jdeps jdk.jdwp.agent jdk.jdi jdk.jfr jdk.jlink jdk.jpackage jdk.jshell jdk.jsobject jdk.localedata jdk.management.jfr jdk.naming.dns jdk.naming.rmi jdk.net jdk.nio.mapmode jdk.random jdk.unsupported.desktop jdk.xml.dom"




