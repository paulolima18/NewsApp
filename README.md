# BBC News app

## Must have
    - Kotlin [x]
    - Run in android latest version [x]
    - Run android Studio 3.0+ without modifications [x]
    - Support portrait and landscape mode [x]
    - Clean Architecture [TODO]
    - Must have unit tests [x]
    - Auth with API key [x]
    - List of top Headlines [x]
        - News provider as title [x]
        - Headlines in list format [x]
        - Each cell should have headline title [x]
        - List sorted by date [x]
        - Must be able to scroll [x]
        - Each cell should have image(downloaded and cashed, not bundled) [x]
    - Headline screen [x]
        - Tapping on an Headline in the Headline list should open the choosen Headline [x]
        - Image, title, description and content are displayed [x]
        - Acommodate missing data use cases [x]

## Optionals
    - Fingerprint Auth [x]
        - Open app with fingerprint if available in device else open normally [x]
        - Main implementation [x]
    - Flavor implementation [x]
        - Different flavors have different news sources [x]

## App flow
Select Flavor with news source 
-> Build 
-> Open app
-> if user has fingerprint request usage
    -> Success enter else close app
    -> No fingerprint enter automatically
-> Setup lib for HTTP/REST with API key 
-> Request list of top Headlines
-> Show sorted(by date) list of top Headlines
-> On Headline click show information

Headline List Screen
Headline Info Screen