C:
cd %USERPROFILE%\AppData\Local\Microsoft\Windows\Explorer\
taskkill /f /im explorer.exe
del thumbcache*
del iconcache*
explorer.exe