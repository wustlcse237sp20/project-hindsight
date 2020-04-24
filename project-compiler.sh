#!/bin/bash
cd "Hindsight/src"
`javac -cp "commons-beanutils-1.9.4.jar:commons-collections4-4.4.jar:commons-lang3-3.10.jar:jsoup-1.13.1.jar:opencsv-5.1.jar:commons-text-1.8.jar" *.java`
`java -cp commons-beanutils-1.9.4.jar:commons-collections4-4.4.jar:commons-lang3-3.10.jar:jsoup-1.13.1.jar:opencsv-5.1.jar:commons-text-1.8.jar:. Main`