package com.hibob.kotlinBasics

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ListManagerTest {


    @Test
    fun `add unique person`() {
        val listManager = ListManager()
        val person = Person("John", 30)
        val result = listManager.addPerson(person)
        assertTrue(result)
    }
    @Test
    fun `add duplicate person throws IllegalArgumentException`() {
        val listManager = ListManager()
        val person = Person("John", 30)
        listManager.addPerson(person)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            listManager.addPerson(person)
        }
        assertEquals("Duplicate person cannot be added.", exception.message)
    }

    @Test
    fun `add multiple people increases list size`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("Jane", 25)
        val person3 = Person("Doe", 40)

        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        assertEquals(3, listManager.getPeople().size)
    }

    @Test
    fun `remove person that exists in the list`(){
        val listManager = ListManager()
        val person1 = Person("John", 30)
        //val person2 = Person("Jane", 25)
        listManager.removePerson(person1)
        assertEquals(false, listManager.getPeopleSortedByAgeAndName().contains(person1))
    }


    @Test
    fun `remove person that does not exist returns false`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val result=listManager.removePerson(person1)
        assertFalse(result)
        assertEquals(false, listManager.getPeopleSortedByAgeAndName().contains(person1))
    }

    @Test
    fun `state of the list after multiple add and remove operations`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("Jane", 25)
        val person3 = Person("Doe", 40)

        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        listManager.removePerson(person2)
        assertEquals(2, listManager.getPeople().size)
        assertTrue(listManager.getPeople().contains(person1))
        assertTrue(listManager.getPeople().contains((person3)))
        assertFalse(listManager.getPeople().contains(person2))
    }

    @Test
    fun `get people sorted by age and name with empty list`() {
        val listManager = ListManager()
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(sortedPeople.size, listManager.getPeople().size)
    }


    @Test
    fun `get people sorted by age and name with one person`() {
        val listManager = ListManager()
        val person = Person("John", 30)
        listManager.addPerson(person)
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(1, sortedPeople.size)
        assertEquals(person, sortedPeople[0])
    }


    @Test
    fun `get people sorted by age and name with multiple people`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("Jane", 25)
        val person3 = Person("Doe", 40)
        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(listOf(person2, person1, person3), sortedPeople)
    }
    @Test
    fun `get people sorted by age and name with edge cases`() {
        val listManager = ListManager()
        val person1 = Person("John", 30)
        val person2 = Person("John", 25)
        val person3 = Person("Jane", 30)
        listManager.addPerson(person1)
        listManager.addPerson(person2)
        listManager.addPerson(person3)
        val sortedPeople = listManager.getPeopleSortedByAgeAndName()
        assertEquals(listOf(person2, person3, person1), sortedPeople)
    }
}