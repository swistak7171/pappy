package pl.kamilszustak.pappy.common.exception

class NoInternetConnectionException : Exception {

    constructor() : super("No Internet connection available")

    constructor(message: String) : super(message)
}