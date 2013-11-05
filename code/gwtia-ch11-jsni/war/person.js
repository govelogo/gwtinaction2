function createPerson(){
   function setCareer(newCareer){
      this.career = newCareer;
   }	
   personObj=new Object();      
   personObj.name="Åsa";     
   personObj.career="scientist";
   personObj.changeCareer = setCareer;
   return personObj;
}