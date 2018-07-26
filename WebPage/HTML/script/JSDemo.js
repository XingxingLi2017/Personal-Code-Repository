/*
  variable
  define: var x = data;
  y = 4; => less rigorous format
  primitive
  number,string,boolean,undefined, null, symbol
*/
var x = 3;
y = 4;
console.log("x = "+x+", y = "+y);
x = "abc"; // weakly-typed
console.log("x = "+x+", y = "+y);
x = 3.45;
console.log("x = "+x+", y = "+y);
x = true;
console.log("x = "+x+", y = "+y);
x = 'c' // '' and "" are both string
console.log("x = "+x+", y = "+y);

/*
  operators
  1. assignment:
    =, +=, -=, *=, /=, %=
  2. arithmetic:
    =,-,*,/,% ,++.--
  3. comparison:
    >,<,>=,<=,!=,==,
  4. logical:
    &&,||,!
  5. bitwise:
    &, |，^, ~，<<,>>,>>>(unsigned left-shifting, filled with 0)
  6. ternary:
    expression?true:false;
*/
// 1. arithmetic:
var a = 3710;
console.log("a="+a/1000*1000); // a=3710

var a1 = 2.3, b1 = 4.7;
console.log("a1+b1 = "+ (a1+b1)); // =7

console.log("12"+1); // '121'
console.log('12'-1);  // 11

//  false == 0 or null
//  true == !{0 or null}, default value is number 1
console.log(true+1); // 2
console.log(2 % 5); //2

var n = 3, m;
m = n++;
console.log("n="+n+",m="+m); // n=3,m=4

// 2. assignment
var i=3;
console.log("i="+i);
i += 2
console.log("i="+i);

// 3. comparison
var z = 3;
console.log(z == 4);
console.log(z != 4);

// 4. logical
var t = 4;
console.log("logical:"+(t>3 && t < 6)); // true
console.log("logical:"+(t<0 || t > 7)); // false
console.log("logical:"+(!t)); // false

// 5. bitwise
console.log( t>3 & t<6); // true & true => 1&1 => 1
console.log(~1); // -2
var c = 6;  // 110
console.log(c&3);   // 2 : 110 & 011 = 010 = 2
console.log(c^3);   // 5 : 110^011 = 101 = 5
console.log(c>>2);  // 001 = 1
console.log((-10).toString(2));   // -1010：in memeory -> 11111111 11111111 11111111 11110110
console.log("-1010 << 2", (-10 << 2).toString(2)); // -101000: in memeory -> 11111111 11111111 11111111 11011000

//   11111111 11111111 11111111 11110110
//-> 11111111 11111111 11111111 11111101
//-> transfer to “-original code” = -11
console.log("-1010 >> 2 = ", (-10 >> 2).toString(2)); //-11 = -3
console.log("(unsigned right shift) -1010 >>> 2 = ",(-10 >>> 2).toString(2)); //111111111111111111111111111101
console.log((-0xff).toString(2));// -11111111
console.log(c<<2); // 11000 = 24


//6. ternary
console.log(3>0?'yes':'no');

/*
  Deferences with java
  1. undefined -> a constant
*/
var xx;
console.log(xx);  // undefined
console.log(typeof(x));
console.log(typeof(true));



/*
  statement
*/
// if
if(4 == x)  // constant at left to prevent errors
  console.log("x=4");
else {
  console.log("x!=4");
}

// switch
var sw = "c";
switch (sw) {
  default:
    console.log("default");
    break;
  case "a":
    console.log("this is a");
    break;
  case "b":
    console.log("this is b");
    break;
}

//while
x=4;
// document object
document.write("<font color='lightblack'>");
while(x>0)
{
  console.log("while loop");

  document.write("while loop<br>");
  x--;
}
document.write('</font>');

// for loop
document.write("<font color='green'>");
for(x=2;x<5;x++)
{
  document.write("for loop<br>");
}
document.write('</font>');
document.write("<hr>");
// for each
var arr=[1,2,3,'hello',true];
var variable=5;
for(var key in arr)
  document.write(arr[key]+"<br>");
var obj={
  key1: "value1",
  "a":"value2",
  3: 57,
  key4:x,
  variable: variable
};
document.write("<hr>");
for(var key in obj)
  document.write(key+","+obj[key]+"<br>");
document.write("<hr>");

document.write("<table cellspacing=5px >");
for(var i = 1 ; i <=9;i++ )
{
  document.write("<tr>");
  for(var j=1 ; j<=i ; j++)
  {
    document.write("<td style='border: 1px solid red'>"+j+"*"+j+"="+(i*j)+"</td>");
  }
  document.write("<tr>");
}
document.write("</table>");

document.write("<hr>");

/*
    Array
*/
//  define
arr = [23, 78, 100];
arr[4] = "lixingxing";  // length is changeable
arr[0] = true;  // can be any data type and function
for(var i = 0 ; i < arr.length ; i++)
  document.write("arr["+i+"] ="+arr[i]+"<br>");
document.write("<hr>");
// define: 2nd type
arr = new Array(5); // length = 5
arr = new Array(4,5,6,true,"xing"); // specify elements
for(var i = 0 ; i < arr.length ; i++)
  document.write("arr["+i+"] ="+arr[i]+"<br>");
document.write("<hr>");


/*
  Function
*/
function demo1(){
  document.write("this is demo1<br>");
  return ;
}
demo1();

function add(x, y=3){
  document.write("arguments=");
  for(var i = 0 ; i < arguments.length ; i++)
    document.write(arguments[i]+" ");
  document.write("<br>");
  return x+y;
}

document.write("add(1,5) = "+add(1,5)+"<br>");
document.write("add(1)="+add(1)+": with default param<br>");
document.write("add(1,5,7,8,11) = "+add(1,5,7,8,11)+"<br>");
// don't have (), the funciton won't be called, print out the code
document.write("add="+add+"<br>");
document.write("<hr>");


// dynamical function
// use Object Function in js
// params: parameters, function body
var sum = new Function("x,y","var sum; sum=x+y; return sum;");
document.write("new Function(): 4+5 = "+sum(4,5)+"<br>");

// anonymous Function
var add = function(a, b){
  return a+b;
};
document.write("anonymous function:11+12="+add(11,12)+"<br>");


/*
    Practices
*/
document.write("<hr>");
document.write("<h3>Practices</h3>");
document.write("Get Maximum<br>");
document.write("Sort<br>");
document.write("Find element<br>");
document.write("Reverse Array<br>");
document.write("<hr>");
arr = [1,34,6,45,-1,34,-5,-6,7,7,-7,0,24];
document.write("target arr = "+arr+"<br>");
// self-invoking
(function(arr){
  var max = Number.MIN_SAFE_INTEGER;
  var min = Number.MAX_SAFE_INTEGER;
  for(var i = 0; i< arr.length ; i++)
  {
    if(arr[i] > max)
      max = arr[i];
    if(arr[i] < min)
      min = arr[i];
  }
  document.write("max value = "+max+'<br>');
  document.write("min value ="+min+'<br>');
})(arr);

// bubbule sort
var bubbleSort = function(arr){
  for(var i = arr.length-1 ; i >0 ; i--)
  {
    for(var j =0; j < i; j++){
      if(arr[j]>arr[j+1]){
        swap(arr,j,j+1);
      }
    }
  }
  function swap(arr,a,b){
    var temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }
};
bubbleSort(arr);
document.write("sort: arr = "+arr);
document.write("<hr>");
document.write("binary search function<br>");

// binary search
function binarySearch(arr, key){
    var left=0;
    var right=arr.length-1;
    while(left < right){
      // differences with java
      // need Math.floor to get integer
      var mid=Math.floor(left+(right-left)/2);
      if(arr[mid] == key){
        return mid
      }
      else if(arr[mid] < key){
        left=mid+1;
      }
      else {
        right=mid;
      }
    }
    if(arr[left] == key)
      return left;
    else
      return -left-1;
}
var key = -5;
document.write("sort: arr = "+arr+'<br>');
document.write("key = "+key+'<br>');
document.write("key's index = "+binarySearch(arr, key)+'<br>');
document.write("<p>mid=Math.foor((left+right)/2), use Math.floor to get integer</p>");
document.write("<hr>");
function println(val){
  document.write(val+"<br>");
}

(function(){
  var key = 5;
  document.write("this is the local 'var key' = "+key+"<br>");
  document.write("this is the global 'var key' = "+window.key+"<br>");
})();
document.write("<hr>");

/*
  Object in javascript
*/
document.write("<h3>Object related content</h3>");

// general methods of object:
// Object.toString(), Object.valueOf()
document.write("<p>");
println("println.toString() = "+println.toString());
document.write("</p>");
println("valueOf() demo");
println("arr.valueOf() = "+arr.valueOf());
println("");
println("<h3>String Object Demo</h3>");


/*
  String
*/
var str = "hello javascript";
println("str = "+str);
println("str.length = "+str.length);
println("str.bold() = "+str.bold());
println("str.charAt(1) = "+str.charAt(1));
println("str.fontcolor('blue') = "+str.fontcolor('blue'));
println("str.indexOf('j') = "+str.indexOf('j'));
println("str.link('https://www.google.com') = "+str.link('https://www.google.com'));
println("str.substr(start=0,length=3) = "+str.substr(0,3));
println("str.substring(start=0,end=3) = "+str.substring(0,3));
println("<p>customize function: trim</p>");
function trim(str){
  for(var i = 0 ; i < str.length ; i++)
  {
    if(str.charAt(i) != ' ')
      break;
  }
  str = str.substring(i, str.length);
  for(i = str.length-1 ; i >= 0 ; i--)
  {
    if(str.charAt(i) != " ")
      break;
  }
  return str.substring(0,i+1);
}
var str2 = "    hello trim function!        ";
println("str2 = -"+str2+"-");
println("trim(str2) = -"+trim(str2)+"-");

// prototype application
// use prototype to extend function for existing Object
// constructure: prototype => class => object
println("<p style='font-size: 1.1em;font-weight: bold;'>add trim() to string's prototype</font></p>");
// use prototype reference
println("str2.myTrim = "+str2.myTrim);
String.prototype.myTrim = function(){
  var left=0;
  var right = this.length-1;
  while(left<=right && this.charAt(left) == ' ')
    left++;
  while(left<=right && this.charAt(right) == ' ')
    right--;
  return this.substring(left,right+1);

}
println("str2.myTrim = "+str2.myTrim);
console.log("'   ab c  '.myTrim() = -"+"   ab c  ".myTrim()+"-");
println("'   ab c  '.myTrim() = -"+"   ab c  ".myTrim()+"-");

/**
  Practice:
  extend functions for String
  toCharArray()
  reverse()
*/
String.prototype.toCharArray = function(){
  var chars = [];
  for(var i = 0 ; i < this.length ; i++)
    chars[i] = this.charAt(i);
  return chars;
}
println("<h3>Practice</h3>");
println("str = "+str);
println("str.toCharArray="+str.toCharArray());

String.prototype.reverse = function(){
  var arr = this.toCharArray();
  for(var i =0,j=arr.length-1 ;i<j ;i++,j--)
    swap(arr,i,j);
  return arr.join("");

  function swap(arr,i,j){
    var temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
println("str.reverse() = "+str.reverse());
println("<hr>");
println("<h3>Array Object</h3>");
/**
  Array
*/

// arr.concat()
var arr = ['nab','haha','hehe','xing','abbc'];
var arr2=['qq','xiaoqiang',70];
println("arr="+arr);
println("arr2="+arr2);
println("arr.concat(arr2) = "+arr.concat(arr2));

//arr.join(separator);
println("arr.join('+') = "+arr.join('+'));
//arr.pop()
println("arr.pop() = "+arr.pop());
println("new arr = "+arr);
//arr.push(element)
//arr.push(arr2) will add arr2 as an element in arr
println("arr.push(arr2) = "+arr.push(arr2));
println("new arr = "+arr);
println("new arr'length = "+arr.length);
println("arr.reverse() = "+arr.reverse());
// shift(), remove the first elements
println("arr.shift()="+arr.shift());
println("<hr>");
println("arr now is = "+arr);
println("arr.slice(0,-1)="+arr.slice(0,-1));
println("arr now is = "+arr);
println("arr2 now is = "+arr2);
// replace elements in the array
println("arr.splice(2,2,arr2) = "+arr.splice(2,2,arr2));
println("after splice, arr = "+arr+", arr.length = "+arr.length);
println("arr.unshift('unshift element') = "+arr.unshift('unshift element'));
println("arr now is = "+arr);

/**
  Date
*/
println("<hr>");
println("<h3>Date Object</h3>");
var date = new Date();
println("date = new Date() => "+date);
println("date.toLocaleDateString() = "+date.toLocaleDateString());
println("date.toLocaleString() = "+date.toLocaleString());
println("date.getFullYear() = "+date.getFullYear()+"-> getYear is discarded.");
println("date.getMonth() = "+date.getMonth()+"-> start with 0");
println("date.getDay() = "+date.getDay()+"-> week day")
println("date.getDate() = "+date.getDate()+"-> the specific date");
println("date.getHours() = "+date.getHours());
println("date.getMinutes() = "+date.getMinutes());
println("date.getSeconds() = "+ date.getSeconds());
println("<hr>");
println("millisecond <=> Date");
println("Date => millisecond:");
println("Date.parse('9/28/18') = "+Date.parse('9/28/18'));
println("date.getTime() = "+date.getTime());
println("millisecond => Date");
println("new Date(1530580727169)="+new Date(1530580727169));

/*
  With statement
*/

println("<hr>");
println("With statement demo");
with(date){
  println("with block: getFullYear()="+getFullYear());
}
println("<hr>");
/*
  Math object
*/
println("<h3>Math</h3>");
println("Math.floor(12.34) = "+Math.floor(12.34));
println("Math.ceil(12.34) = "+Math.ceil(12.34));
println("Math.round(12.34) = "+Math.round(12.34));
println("Math.round(12.54) = "+Math.round(12.54));
println("Math.pow(10,3) = "+ Math.pow(10,3));
println("Math.random() = "+Math.random()+"-> [0,1)");

/*
  Global Method
*/
println("<hr>");
println("<h3>Global Methods</h3>");
println("parseInt()");
for(var i = 0 ; i < 10 ; i++){
  println("random number"+i+" = "+parseInt(Math.random()*10));
}
println("parseInt('0x110', 16) = "+parseInt('110',2));
println("isNaN(12) = "+isNaN(12));

/*
  Number
*/
println("<h3>Number</h3>")
println("new Number(6).toString(2) = "+new Number(6).toString(2));
/*
  For in statement
*/
println("");
println("traverse object -> for in statement")
for( i in arr){
  println("for( i in arr) -> i = "+i);
  println("for( i in arr) -> arr[i] = "+arr[i]);
}
println("<hr>");

/*
  customized Object
*/
println("<h3>customized Object</h3>")
println("use function to imitate OOP");

function Person(){ // the equivalent of constructor
  println("i'm person.");
};
var p1=new Person();
// add fields dynamically
p1.age=13;
p1.name="Xing";
p1.show=function(){
  // use this to access the fields of this Object
  println("Person.show is running. age = "+this.age+", name = "+this.name);
};
p1.show();

// use constructor to define object

function Chinese(name, age){
  this.name=name;
  this.age=age;
  this.setName=function(name){
    this.name=name;
  };
  this.getName=function(){
    return this.name;
  }
};
var p2=new Chinese("yang", 24);
println("p2.name="+p2.name);
println("p2.setName('zhang san')="+p2.setName('zhang san'));
println("p2.getName()="+p2.getName());

// use javascript Object Notation
println("<hr>")
println("use JSON to get Object");
var p3={
  name: 'person3',
  getName: function(){
    return this.name;
  },
  setName:function(name){
    this.name=name;
  }
}
println("p3.name="+p3.name);
println("p3.setName('zhang san')="+p3.setName('zhang san'));
println("p3.getName()="+p3.getName());
println("p3['getName']="+p3['getName']);

// use for in to traverse Object
println("<hr>");
for(i in p2){
  println("i="+i+", p2[i] = "+p2[i]);
  if(i == 'getName'){
    println(p2[i]());
  }
}









//
