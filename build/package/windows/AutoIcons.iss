;This file will be executed next to the application bundle image
;I.e. current directory will contain folder AutoIcons with application files
[Setup]
AppId={{fxApplication}}
AppName=AutoIcons
AppVersion=1.0
AppVerName=AutoIcons 1.0
AppPublisher=AutoIcons
AppComments=AutoIcons
AppCopyright=Copyright (C) 2017
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={pf}\AutoIcons
DisableStartupPrompt=Yes
DisableDirPage=No
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=AutoIcons
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=AutoIcons-1.0
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
SetupIconFile=AutoIcons\AutoIcons.ico
UninstallDisplayIcon={app}\AutoIcons.ico
UninstallDisplayName=AutoIcons
WizardImageStretch=No
WizardSmallImageFile=AutoIcons-setup-icon.bmp   
ArchitecturesInstallIn64BitMode=x64


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "AutoIcons\AutoIcons.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "AutoIcons\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\AutoIcons"; Filename: "{app}\AutoIcons.exe"; IconFilename: "{app}\AutoIcons.ico"; Check: returnTrue();
Name: "{commondesktop}\AutoIcons"; Filename: "{app}\AutoIcons.exe";  IconFilename: "{app}\AutoIcons.ico"; Check: returnTrue();

[Run]
Filename: "{app}\AutoIcons.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\AutoIcons.exe"; Description: "{cm:LaunchProgram,AutoIcons}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\AutoIcons.exe"; Parameters: "-install -svcName ""AutoIcons"" -svcDesc ""AutoIcons"" -mainExe ""AutoIcons.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\AutoIcons.exe "; Parameters: "-uninstall -svcName AutoIcons -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support? 
  Result := True;
end;

