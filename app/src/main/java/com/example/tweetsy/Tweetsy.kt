package com.example.tweetsy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Tweetsy : Application()

/*

Navigtain composnse to used to navigate in b/w composable screen
Navhost

area where render UI
navgraph

if you are in one positin, where you cna go from there,
these informatin will represent by nav graph


navcontroller

navcontroller to intereact navhost and navraph
and managae backstack as well



navarguemnt -

one more component is navargument supppost we have screen there is many products
and if any product click we need to navigate to other screen with detail of that product
we need to tell send id to other screen - is called navarguemnt . ki ye dikahana hai

to send data from one screen to other.

currently we have two screen
category and detail screen



 */




/* viewmodel scope -
if mainactivty created viewmodel, then mainactity \
screen se viewmodel attach rhe to
screen ka sath nhi navstack ke sath juda hota hai
jab tak backstack rhegi , tb tk viewmodel rhaga otherwise destory ho jayga
* */
