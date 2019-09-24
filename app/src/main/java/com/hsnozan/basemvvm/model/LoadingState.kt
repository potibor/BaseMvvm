package com.hsnozan.basemvvm.model

class LoadingState(var state : State , var errorMessage : String?= null)

enum class State{
    LOADING , ERROR , SUCCESS
}