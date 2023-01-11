/** Person.java
 * This class defines a person with attributes for name and birth/death
 *  dates.
 * As with a real Person, there must always be a date of birth, and if 
 *  the Person has a date of death, then the date of death is equal to 
 *  or later than the date of birth.
 */
public class Person implements Cloneable {
    private String name;
    private Date born;
    private Date died; // null indicates still alive.

    /** Argument constructor to create a Person (with no death date).
     * @param initialName
     * @param birthMonth - int
     * @param birthDay - int
     * @param birthYear - int
     */
    public Person(String initialName, int birthMonth, int birthDay, 
          int birthYear) {
       // ITP 220 - complete this constructor
       // implement using the this() constructor
       this.name = initialName;
       this.born = new Date(birthMonth, birthDay, birthYear);
    }
    
    // difference between two constructors is birthMonth (String vs int)

    /** Argument constructor to create a Person (with no death date).
     * @param initialName
     * @param birthMonth - String
     * @param birthDay - int
     * @param birthYear - int
     */
    public Person(String initialName, String birthMonth, int birthDay, int birthYear) {
       // ITP 220 - complete this constructor
       // implement using the this() constructor
       this.name = initialName;
       this.born = new Date(birthMonth, birthDay, birthYear);
    }

    /** Argument constructor to create a Person (with a death date).
     * @param initialName
     * @param birthMonth - String
     * @param birthDay - int
     * @param birthYear - int
     * @param deathMonth - String
     * @param deathDay - int
     * @param deathYear - int
     */
    public Person(String initialName, String birthMonth, int birthDay, 
          int birthYear, String deathMonth, int deathDay, int deathYear) {
       // ITP 220 - complete this constructor
       this.name = initialName;
       this.born = new Date(birthMonth, birthDay, birthYear);
       this.died = new Date(deathMonth, deathDay, deathYear);
    }

    // again, above is String birthMonth/deathMonth, below is int values 

    /** Argument constructor to create a Person (with a death date).
     * @param initialName
     * @param birthMonth - int
     * @param birthDay - int
     * @param birthYear - int
     * @param deathMonth - int
     * @param deathDay - int
     * @param deathYear - int
     */
    public Person(String initialName, int birthMonth, int birthDay,
          int birthYear, int deathMonth, int deathDay, int deathYear) {
       // ITP 220 - complete this constructor
       this.name = initialName;
       this.born = new Date(birthMonth, birthDay, birthYear);
       this.died = new Date(deathMonth, deathDay, deathYear);
    }

    /** Argument constructor to create a Person.
     * @param initialName
     * @param birthDate
     * @param deathDate
     */
    public Person(String initialName, Date birthDate, Date deathDate) {
       set(initialName, birthDate, deathDate);
    }

    /** Copy constructor.
     * @param original - object (Person) whose attributes will be used
     *  to create this new Person.
     */
    public Person(Person original) {
       if (original != null) {
          set(original.getName(), original.getBirthDate(),
             original.getDeathDate());
       }
    }

    /** Method to set all attributes of this Person from supplied
     * arguments.
     * @param initialName
     * @param birthDate
     * @param deathDate
     */
    public void set(String newName, Date birthDate, Date deathDate) {
       if (consistent(birthDate, deathDate)) {
          name = newName;
          born = new Date(birthDate);
          if(deathDate == null) {
             died = null;
          } else {
             died = new Date(deathDate);
          }
       }
    }

    /** Method to display this Person't attributes in a user-friendly
     *  printout.
     */
    public String toString( ) {
       return name + ", " + born + "-" + died;
    }

    /** Method to determine whether this Person has the same attributes
     *  as the passed Person.
     * @param otherPerson - Person being compared to this
     */
    public boolean equals(Person otherPerson) {
       if (otherPerson == null) {
          return false;
       } else {
          // check NAME
          if((name == null && otherPerson.getName() != null) ||
             (name != null) && !name.equals(otherPerson.getName()) ) {
             return false;
          }
          
          // check Dates
          return 
             datesMatch(getBirthDate(), otherPerson.getBirthDate()) &&
             datesMatch(getDeathDate(), otherPerson.getDeathDate());
       }
    }

    /** Method to match date1 and date2, which must either be the same
     *  dates or both null.
     */
    private boolean datesMatch(Date date1, Date date2) {
       if (date1 == null) {
          return (date2 == null); // true if date2 is also null
       } else if (date2 == null) { // start tests where date1 is not null
          return false;
       } else {
          return(date1.equals(date2));
       }
    }
    
    /** Method that verifies whether the supplied birth/death dates are
     *   consistent with each other.  Rules are:
     *    1. Birth Date must not be null
     *    2. If death date is not null, birthDate must come before or 
     *       be equal to the deathDate.
     */
    private boolean consistent(Date birthDate, Date deathDate) {
       if (birthDate == null) {
          return false;
       } else if (!birthDate.dateOK(birthDate.getMonth(),
            birthDate.getDay(), birthDate.getYear())) {
       	  return false;
       } else if (deathDate == null) {
          return true; // nothing else to check if it makes it here
       } else if (!deathDate.dateOK(deathDate.getMonth(),
            deathDate.getDay(), deathDate.getYear())) {
          return false;
       }
       return birthDate.compareTo(deathDate) <= 0;
    }

    /** Method to set birth date.  Before setting it to newDate,
     *  checks that it is valid with the existing death date.
     */
    public void setBirthDate(Date newDate) {
       if (consistent(newDate, died)) {
          if (newDate == null) {
          	born = null;
          } else {
          	born = new Date(newDate);
          }
       }
    }

    /** Method to set death date.  Before setting it to newDate,
     *  checks that it is valid with the existing birth date.
     */
    public void setDeathDate(Date newDate) {
       if (consistent(born, newDate)) {
          if (newDate == null) {
          	died = null;
          } else {
          	died = new Date(newDate);
          }
       }
    }

    /** Method to reset only the birth year.
     *  This method does nothing if date of birth is NULL.
     */
    public void setBirthYear(int newYear) {
       if (born == null) {
          return;
       }
       
       // set birth year by creating a new Date and updating its
       //  year, then validate this as a possible birth date before
       //  setting = born
       Date updatedDate = new Date(born);
       updatedDate.setYear(newYear);
       if (consistent(updatedDate, died)) {
          born = updatedDate;
       }
    }

    /** Method to reset only the death year.
     *  This method does nothing if date of death is NULL.
     */
    public void setDeathYear(int newYear) {
       if (died == null) {
          return;
       }

       // set death year by creating a new Date and updating its
       //  year, then validate this as a possible death date before
       //  setting = died       
       Date updatedDate = new Date(died);
       updatedDate.setYear(newYear);
       if (consistent(born, updatedDate)) {
          died = updatedDate;
       }
    }

    public String getName( ) {
       return name;
    }
    public void setName(String newName) {
       name = newName;
    }

    public Date getBirthDate( ) {
       if(born != null) {
          return new Date(born);
       } else {
          return null;
       }
    }

    public Date getDeathDate( ) {
       if(died != null) {
          return new Date(died);
       } else {
          return null;
       }
    }

    public Object clone() throws CloneNotSupportedException {
      return super.clone();
    }

    /** Method to determine if this Date precedes another one.
    * @param otherDate - Date being compared
    * @return boolean - TRUE if this Date precedes otherDate
    */
   public boolean precedes(Person.Date otherDate) {
      if(otherDate == null) 		
          return true;
          
      Person.Date thisDate = this.new Date();
      // check the conditions of precedes in order, returning false when
      //  1. Year is > otherDate's year
      //  2. Year = otherDate's year, but month > otherDate's month
      //  3. Year and month = otherDate's, but day > otherDates's day
      return (thisDate.year < otherDate.getYear()) || 
               ((thisDate.year == otherDate.getYear()) && 
              (thisDate.getMonthIndex(thisDate.month) < otherDate.getMonthIndex(otherDate.getMonth()))) || 
               ((thisDate.year == otherDate.getYear()) && 
              (thisDate.getMonthIndex(thisDate.month) == otherDate.getMonthIndex(otherDate.getMonth()) && 
               (thisDate.day < otherDate.getDay())));
   }

   
/** Date.java
 * This class creates a Date object with attributes for month, day and
 *  year.  
 * Note:  This is not the same as java.util.Date
 */

public class Date implements Comparable, Cloneable {
   /** Static Array of valid months (in month index order) */
   private final String[] VALID_MONTHS = new String[] {
      "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
   };

   /** Month, as a String, initially NULL */
   private String month; 

   private int day = 0;  // initially invalid
   private int year = 0;  // initially invalid

   /** Empty constructor.
    * Sets default month, day and year to Jan. 1, 2000
    */
   public Date( ) {
      month = VALID_MONTHS[0];
      day = 1;
      year = 2000;
   }

   /** Argument constructor.
    * @param monthInt - month as an int
    * @param day
    * @param year
    */
   public Date(int monthInt, int day, int year) {
      setDate(monthInt, day, year);
   }

   /** Argument constructor.
    * @param monthString - month as a String
    * @param day
    * @param year
    */
   public Date(String monthString, int day, int year) {
      setDate(monthString, day, year);
   }

   /** Argument constructor.  Sets Date to the first day of the year.
    * @param year - initialize this Date to the first day of this year.
    */
   public Date(int year) {
      setDate(year);
   }

   /** Argument constructor. 
    * @param aDate - Date to base this new Date instance on.
    *  If null, this date will have no valid month set.
    */
   public Date(Date aDate) {
      if (aDate != null) {
         month = aDate.getMonth();
         day = aDate.getDay();
         year = aDate.getYear();
      }
   }

   /** Method to set this Date to the first day of the given year.
    * @param year - initialize this Date to the first day of this year
    * @return boolean - TRUE if year was valid    
    */
   public boolean setDate(int year) {
      return setDate(VALID_MONTHS[0], 1, year);
   }

   /** Method to set the attributes for this Date instance.
    * @param monthInt - month as an int
    * @param day
    * @param year
    * @return boolean - TRUE if date entries were valid    
    */
   public boolean setDate(int monthInt, int day, int year) {
      return setDate(getMonthString(monthInt), day, year);
   }

   /** Method to set the attributes for this Date instance.
    * @param monthString - month as a String
    * @param day
    * @param year
    * @return boolean - TRUE if date was valid
    */
   public boolean setDate(String monthString, int day, int year) {
      if (dateOK(monthString, day, year)) {
         month = monthString;
         this.day = day;
         this.year = year;
         return true;
      }
      return false;
   }

   /** Method to validate and set the year for this Date
    * @param year - int
    */
   public void setYear(int year) {
      if(yearOk(year)) {
         this.year = year;
      }
   }

   /** Method to validate the year
    * @param year - int
    * @return boolean - TRUE if year was valid
    */   
   public boolean yearOk(int year) {
      // ITP 220 - validate year, assuming 1900 is the earliest
      // completing this program in 2022 means that a person cannot be born in 2023 or greater 
      //  year possible
      if (year < 1900 || year > 2022) { // earlier than 1990 or later than 2022
         return false;
      }
      else {
         return true;
      }

   }

   /** Method to validate and set the month for this Date
    * @param monthIndex - int - month index
    * @return boolean - TRUE if month was valid
    */
   public void setMonth(int monthIndex) {
      String monthStr = getMonthString(monthIndex);
      if (monthStr != null) {
         month = monthStr;
      }
   }

   /** Method to set the day for this Date after checking that it is
    *  valid for the month that is currently set.
    * @param day - valid day
    */
   public void setDay(int day) {
      if(dayOk(day)) {
         this.day = day;
      }
   }

   /** Method to validate the day of a month
    * @param day - int
    * @return boolean - TRUE if day was valid
    */   
   public boolean dayOk(int day) {
      return dayOk(day, month);
   }

   /** Method to validate the day of a month
    * @param day - int
    * @param month - month to validate this day against
    * @return boolean - TRUE if day was valid
    */   
   public boolean dayOk(int day, String month) {
      // ITP 220 - validate days for the month, assume February
      //  can have 28 days (do not worry about leap years)
      
      // if the month has 31 days, day cannot be greater than 31
      if (month == "January" || month == "March" || month == "May" || month == "July"
      || month == "August" || month == "October" || month == "December") { 
         if (day <= 31) {
            return true;
         }
      }
      
      // if the month has 30 days, day cannot be greater than 30
      else if (month == "April" || month == "June" || month == "September" || month == "November") {
         if (day <= 30) {
            return true;
         }
      }

      // if the month is February, day cannot be greater than 28 
      else {
         if (day <= 28) {
            return true;
         }
      }
      // if the day is negative or 0, will return false
      return false;
   }

   /** Equals method to compare this Date to another Object. 
    *  @param other - Object being compared to 'this'
    *  @return - TRUE if other contains the same information as 'this'
    */
   public boolean equals(Date otherDate) {
      if (otherDate == null) {
         return false;
      } else {
         // check MONTH String
         if((month == null && otherDate.getMonth() != null) ||
            ((month != null) && !month.equals(otherDate.getMonth())) ) {
                  return false;
         }

         // check day and year ints
         return (day == otherDate.getDay()) &&
         (year == otherDate.getYear());
      }
   }

   /** Method to determine if this Date precedes another one.
    * @param otherDate - Date being compared
    * @return boolean - TRUE if this Date precedes otherDate
    */
   public boolean precedes(Date otherDate) {
      if(otherDate == null) 		
          return true;
          
      // check the conditions of precedes in order, returning false when
      //  1. Year is > otherDate's year
      //  2. Year = otherDate's year, but month > otherDate's month
      //  3. Year and month = otherDate's, but day > otherDates's day
      return (year < otherDate.getYear()) || 
               ((year == otherDate.getYear()) && 
              (getMonthIndex(month) < otherDate.getMonthIndex(otherDate.getMonth()))) || 
               ((year == otherDate.getYear()) && 
              (getMonthIndex(month) == otherDate.getMonthIndex(otherDate.getMonth()) && 
               (day < otherDate.getDay())));
   }

   /** Method to return whether all 3 parameters create a valid Date
    * @param monthInt 
    * @param dayInt
    * @param yearInt
    * @return boolean - TRUE if Date is valid
    */
   private boolean dateOK(int monthInt, int dayInt, int yearInt) {
      return dateOK(getMonthString(monthInt), dayInt, yearInt);
   }

   /** Method to return whether all 3 parameters create a valid Date
    * @param monthString
    * @param dayInt
    * @param yearInt
    * @return boolean - TRUE if Date is valid
    */
   private boolean dateOK(String monthString, int dayInt, int yearInt) {
      return (monthOk(monthString) && dayOk(dayInt, monthString) && 
            yearOk(yearInt));
   }

   /** Internal method to validate a month String.
    *  @param month - month String to validate.
    *  @return boolean - TRUE if the month is valid
    */
   private boolean monthOk(String month) {
      if(month == null) {
         return false;
      }

      return getMonthIndex(month) >= 0;
   }

   /** Internal method to convert a month index to its corresponding 
    *   month String.
    *  @param monthNumber - month index where month=1 is January and month=12 is December
    *  @return String - month as a String
    */
   private String getMonthString(int monthNumber) {
      if(monthNumber > 0 && monthNumber <= VALID_MONTHS.length) {
         // retrieve the month found at monthNumber-1 (1 for the
         //  0 index position (not a valid month index)
         return VALID_MONTHS[monthNumber-1];
      }
      return null;
   }

   /** Internal method to convert a month String to its corresponding 
    *   month index.
    *  @param monthStr - month String
    *  @return int - gets month index for the current month, 0 indicates
    *   that a valid month has not been set.
    */   
   public int getMonthIndex(String monthStr) {         
      // ITP 220 - retrieve valid month index for the given month String
      // Remember, array begins with 0, so January's index is 0

      // loops through the array until match is found
      for (int idx = 0; idx < VALID_MONTHS.length; idx++) {
         if (monthStr.equals(VALID_MONTHS[idx])) { // if the months match
            return idx; // return the index of the element
         }
      }
      // if this date's month is not valid, return -1
      return -1;
   }

   public int getDay() {
      return day;
   }

   public int getYear() {
      return year;
   }

   public String getMonth() {
      return month;
   }

   public String toString( ) {
      return (month + " " + day + ", " + year);
   }
}

//public Object clone() throws CloneNotSupportedException {
 //  return super.clone();
// }

public int compareTo(Person.Date date) {
   if (this.precedes(date)) {
      return -1;
   }
   else if (this.equals(date)) {
      return 0;
   }
   else {
      return 1;
   }
}


}