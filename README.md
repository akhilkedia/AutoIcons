# ![AutoIcons](https://raw.githubusercontent.com/akhilkedia/AutoIcons/master/build/package/windows/AutoIcons-setup-icon.bmp)AutoIcons - Automatically download and add beautiful icons to your windows folders

[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-green.svg)](http://www.gnu.org/licenses/gpl-3.0) [![Donate](https://img.shields.io/badge/Donate-PayPal-blue.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=UY6TVJXST724J)

## Table of Contents

 * [What AutoIcons does](#what-autoicons-does)
 * [ScreenShots](#screenshots)
   + [Below are some screenshots of AutoIcons giving Awesome icons to folders](#below-are-some-screenshots-of-autoicons-giving-awesome-icons-to-folders)
   + [Below are some of the screenshots of AutoIcons app](#below-are-some-of-the-screenshots-of-autoicons-ui)
 * [How to install AutoIcons](#how-to-install-autoicons)
 * [How to build AutoIcons](#how-to-build-autoicons)
 * [How to use AutoIcons](#how-to-use-autoicons)
 * [How to get Google API Key](#how-to-get-google-api-key)
 * [How to get Google Search Engine ID](#how-to-get-google-search-engine-id)
 * [TroubleShooting](#troubleshooting)
 * [Donations](#donations)
 * [License](#license)
 
## What AutoIcons does

AutoIcons finds beautiful icons for your windows folders, and downloads and applies them to your windows folders.

## ScreenShots

### Below are some screenshots of AutoIcons giving Awesome icons to folders

![](https://github.com/akhilkedia/AutoIcons/raw/master/ScreenShots/AutoIcons_Screenshot.png)

### Below are some of the screenshots of AutoIcons UI

![](https://github.com/akhilkedia/AutoIcons/raw/master/ScreenShots/AutoIcons_UI.png)


## How to install AutoIcons

1. Simply download the windows installer from [the Github Releases Page](https://github.com/akhilkedia/AutoIcons/releases).

## How to build AutoIcons

1. Clone the Git repo.
2. Build with Ant - Run the file `/build/build.xml` with Ant. This will also generate windows installer binary.
  * Alternatively, import the repo in Eclipse and press build. This will not create the windows installer binary.

## How to use AutoIcons

This application **requires** you to sign up for **free** a Api Key from Google. See instructions below on how to get the keys.
This application **requires** you to create a **free** a Custom Search Engine ID from Google. See instructions below on how to get the ID.

1. Run the application from the Desktop Shortcut or from `Program Files/AutoIcons/AutoIcons.exe`.
2. Fill in the values of `API Key from Google`, `Custom Search Engine ID for Google Search`. (See instructions below on how to get these)
3. Browse and select the path of the top-most folder you want to "Iconize" - i.e., give a beautiful icon.
4. Select the "Depth" of folder you would like to Iconize - Giving this a value of `0` only give the above folder in point `3.` the icon.
  Giving this a value of `1` will give its subfolders icons as well. Giving a value of `2` will iconize sub-folders of sub-folders as well, and so on.
5. Press the button "Run AutoIcons with Above Settings"


## How to get Google API Key

1. Go to [https://console.developers.google.com/apis/dashboard](https://console.developers.google.com/apis/dashboard) and login with your Google Account.
2. Click the `Enable API` button at the top.
3. Search and select `Custom Search API`
4. Click `Create Project`. Click `Create` again.
5. Use the default `My Project` name, or give your project any other name. Click `Create Project` again.
6. Now click `Enable` to enable `Custom Search API`.
7. Click `Create Credentials`
8. Click `What Credentials do I need?`
9. You now have your credentials. Copy and paste this in the `API Key from Google` section of AutoIcons.
  
The API Key is something like `"AIzaSy...."`

## How to get Google Search Engine ID

1. Go to [https://cse.google.com/manage/all](https://cse.google.com/manage/all) and login with your Google Account.
2. Click on `Add` below search `Edit Search Engines`
3. In `Sites to Search`, enter `www.findicons.com`
4. In `Name of the Search Engine`, give it any name.
5. Click `Create`
6. Click `Control Panel` next to `Modify your search engine`
7. Turn on the toggle next to the option `Image Search`
8. In `Sites to Search`, click `Search only included sites` and change it to `Search the Entire Web but emphasize included sites`
9. Click `Search Engine ID` next to `Details`.
10. You now have your Search Engine ID. Copy and paste this in the `Custom Search Engine ID for Google Search` section of AutoIcons.

The Search Engine ID is something like `1234...:ab12..`

## TroubleShooting

There is a free API limit of 100 requests/day from Google, so only 100 folders can be given icons in 1 day. If you give any extra folders, it will fail.
Wait 24 hours, and when you run AutoIcons next time, check the box `Check this box to only run on folders failed last time`.
This will give icons to any folders that failed previously due to reach the limit of 100 requests/day.

**Note** - It is possible to pay Google to get higher limits of upto 10,000 requests/day.

## Donations
If you like this project, buy me a cup of coffee! :) 

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=UY6TVJXST724J)

## License

This program is AutoIcons
Copyright (C) 2017  Akhil Kedia

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.