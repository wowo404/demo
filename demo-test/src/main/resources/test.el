def add(a, b){
	a + b;
}
def hello(){
	System.out.println("hello!");
	int a = add(1,2);
	System.out.println("function call function:" + a);
	System.out.println("false == 1:" + false == 1);
	System.out.println("false == 0:" + false == 0);
}
hello();

x = 13;
do{
	System.out.println("do until loop:" + x);
	x --;
}
until(x < 10);

num = 0;
while (num < 10){
	System.out.println("while loop:" + num);
	num++;
}
