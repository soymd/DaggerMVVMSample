package io.github.soymd.daggermvvm.main

@Suppress("PropertyName")
class MainRepositoryFake : MainRepository {

    var saveCount_arg: Int? = null
    override fun saveCount(count: Int) {
        saveCount_arg = count
    }

    var getCount_returns = -1
    override fun getCount(): Int {
        return getCount_returns
    }
}