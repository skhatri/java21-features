# java21-features
Features Tests and Examples

| Feature                 |Example|JEP|Status|Version|
|-------------------------|---|---|---|---|
| Multiline               |```./gradlew runApp -Pfeature=multiline -q```|[JEP 378](https://openjdk.org/jeps/378)|Final|15|
| Switch Pattern Matching |```./gradlew runApp -Pfeature=switchpattern -q```|[JEP 441](https://openjdk.org/jeps/441)|Final|21|
| Sealed Classes          |```./gradlew runApp -q -Pfeature=sealed```|[JEP 409](https://openjdk.org/jeps/409)|Final|17|
| Simple Web Server       |```jwebserver -b 0.0.0.0 -p 8040```|[JEP 408](https://openjdk.org/jeps/408)|Final|18|
| Record Patterns         |```./gradlew runApp -q -Pfeature=recordpattern```|[JEP 440](https://openjdk.org/jeps/440)|Final|21|
| Packaging Tool*         |```./gradlew clean build && jpackage --name sealed --input build/libs --main-jar java21-features.jar --main-class sealed.App```|[JEP 392](https://openjdk.org/jeps/392)|Final|16|
| Generational ZGC**      |```./gradlew runApp -q -Pfeature=zgc```|[JEP 439](https://openjdk.org/jeps/439)|Final|21|
|Hidden Class|```./gradlew runApp -q -Pfeature=hidden```|[JEP 371](https://openjdk.org/jeps/371)|Final|15|


'* - Packaged tool can be found in your applications and can be run like this in MacOS - /Applications/sealed.app/Contents/MacOS/sealed
'** - zgc performance can be inspected by running something like ```h2load -c50 -m20 --duration=120 --warm-up-time=5 --h1 http://localhost:8040/time``` 
