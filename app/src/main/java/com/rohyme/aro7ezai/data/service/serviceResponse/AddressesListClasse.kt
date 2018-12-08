package com.rohyme.aro7ezai.data.service.serviceResponse

import java.io.Serializable
import java.util.ArrayList

class AddressesListClasse(val addressesList : List<AddresseModel>) :Serializable {
val addresses = ArrayList(addressesList)
}