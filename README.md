# Records Store



Records Store is Android Assignment for Grapes'n'Berries company . 
contains  3 Screens

  - Home Screen
  - Record Details Screen
  - Favourite screen

# About Application programming :
- Application written in [Kotlin](https://en.wikipedia.org/wiki/Kotlin_(programming_language)) 
- Minimum target platform is Android API 19

# Architecture :
  - [Clean Architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) with [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)  in Presenter
  
# Libraries used (important Libs):
 - RxJava , RxAndroid 
 - Retrofit in consuming Web Services
 - Picasso for images loading
 - Dagger 2 for Dependence Injection
 - Room for DataBase (Not Used , But there is Code as sample of its usage inside application)
 - Architecture components android (LiveData , ViewModel)
 - Calligraphy for fonts 
 - sdp & ssp for Handling Sizes dimensions
 - Constraint layout for designing XMLs
 - PrettyStateView for Handling view states (Mine)
 - PrettyListView for Making lists adapters more sample (Mine) 
 - FlyToCartAnimation : I have used open source code and add some modification on it  .  Link is [HERE](https://github.com/matrixdevz/FlyToCartAnimation) 
 
# WebServices 
I have made small Spring application written in spring and deploy it on HeroHu for service 
  - You can check the  webservice spring application on gitHub from [HERE](https://github.com/Rohyme/RecordStoreWS)
  - You can download postMan collection from [HERE](https://drive.google.com/open?id=183dgffJMN82tFM_mv0OLaMcMta9GP8gf)
 
# Folder Structure

```
├── app .(main package)
│    ├── data
│          ├── cacheData      # used for saving data to internal data base(Room sample)
│          ├── RemoteData     # interface contains abstract functions  and class for its implementation
│          ├── repositories   # has remote and cache injected in it , used to fetch data from db or server
│          ├── services       # has Service api interface
│    ├──  domain # Business holder 
│          ├── entities       # has business Models 
│          ├── interactors    # has abstract use case classes
│          ├── useCases       # has use cases for every logic case can be used in applicaion , has repository injected into it
│    ├── presenter  # contains view  , Used MVVM in it
│          ├──  utils         # utils needed in the application
│          ├──  dependency injection (di) has dagger commponents and modules
│          ├──  app           # has Application class
│          ├──  ui            # has Views (Activities and Fragments ) and their controllers (viewModels)
```
# Install
 - Download Application APK From [HERE](https://drive.google.com/open?id=1SIzKtGN2UmNySQm6TO_aav3vhq3bW6ii) 
 - If Install from unknown resources isn't checked  : 
    - Go to Security >> Device administration.
    - Check "Unknown Sources"
    

# Licence 
Copyright © 2018, [Rohyme](https://github.com/Rohyme/) . Released under the MIT License.

