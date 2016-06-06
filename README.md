## Silanis Lottery

[![Build Status](https://travis-ci.org/zt1983811/silanis_lottery.svg?branch=master)](https://travis-ci.org/zt1983811/silanis_lottery)
[![Coverage Status](https://coveralls.io/repos/github/zt1983811/silanis_lottery/badge.svg?branch=master)](https://coveralls.io/github/zt1983811/silanis_lottery?branch=master)
[![codecov](https://codecov.io/gh/zt1983811/silanis_lottery/branch/master/graph/badge.svg)](https://codecov.io/gh/zt1983811/silanis_lottery)
[![Dependency Status](https://www.versioneye.com/user/projects/57557c0f7757a0004a1de08f/badge.svg?style=flat)](https://www.versioneye.com/user/projects/57557c0f7757a0004a1de08f)

#### Description: 
Lottery machine follow this [requirment](../master/REQUIRMENT.md)

#### System Requirment:
Project require Java 8

#### How to run project:
1. ``` git clone https://github.com/zt1983811/silanis_lottery.git ```
2. ``` ./gradlew build ```
3. ``` java -jar build/libs/silanisLottery-1.0.jar ```

#### User Guide:

###### How to purchase: 
* In console type ```purchase``` and hit enter key
* output ```Please enter first name:```
* In console type ```replace with your first name``` and hit enter key
* output ```Thanks for you purchase, your ball number is: 3 ```

###### How to draw the winners: 
* In console type ```draw``` and hit enter key
* output ```Winner has been drawn```

###### How to display the winners: 
* In console type ```winners``` and hit enter key
* output
```
1st ball 2nd ball 3rd ball

Chris: 97.5$ Tom: 19.5$ Coke: 13.0$
```

###### How to restart another draw: 
* After draw the winners you need to restart draw for user to purchase
* In console type ```restart``` and hit enter key
* output ```New draw has been start```

###### How to quit: 
* In console type ```quit``` and hit enter key

###### Notice:
* Minmum 3 participants required before draw winners
* Maxmum 50 participants allowed
* Every draw inital pot is 200$, and total pot is depence on participant

#### Generate Eclipse project:
``` ./gradlew clean eclipse ```

#### Check the test:
``` ./gradlew clean test ```
