package br.com.alissonbolsoni.bluestore.dataprovider.exceptions

import java.lang.Exception

class EmptyGenresException(message: String, cause: Throwable? = null) : Exception(message, cause)

class SpotifyImportException(message: String = "", cause: Throwable? = null) : Exception(message, cause)

class NotExistsAlbumException(message: String = "", cause: Throwable? = null) : Exception(message, cause)

class NotExistsOrderException(message: String = "", cause: Throwable? = null) : Exception(message, cause)