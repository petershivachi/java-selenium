# Java Selenium
Using Selenium in Java to write code for:

1. Creating a google.com account
2. Using the google account created in step 1, create an account in Jumia.com
3. Login to the account created in step 2 above and search for the product
   “ailyons hd-199a electric dry iron box silver &amp; black (1 yr wrty)”
4. Add the product in step 3 to cart
5. Navigate to cart
6. Send a summary report to email showing the status of the 5 tests above.

## Prerequisites
1. Java 17 SDK or Above
2. Selenium Library

## Setup selenium with chrome driver on ubuntu/debian
### Step 1: update the all packages, If necessary
```
# apt update
# apt upgrade
```

### Step 2: Download 'google-chrome' stable package
```bash
# wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
```

### Step 3: Install 'google-chrome'
```bash
# apt-get install -y ./google-chrome-stable_current_amd64.deb
```

### Step 4: Check installed 'google-chrome' version
```bash
# google-chrome --version
Google Chrome 123.0.6312.86
```

### Step 5: Install selenium, webdriver-manager
- https://pypi.org/project/webdriver-manager/

```bash
# pip3 install selenium
# pip3 install webdriver-manager
```

## Run the project
1. Clone the project
2. Open the project in your favorite IDE
3. Run the project and appropriately supply the necessary fields with valid details
4. Transfer the code to the directory you want to install the application
