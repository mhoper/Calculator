package com.legendleo.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * �������е����⣬����
 * @author legendleo
 *
 */
public class CalculatorIn {
	private Stack<Double> operandStack=new Stack<Double>();//��������ջ  
    private Stack<String> operatorStack=new Stack<String>();//��������ջ  
    private String expression;//�������ʽ  
    private double result=0.0;//������  
    private Map<String,Integer> priorityMap=new HashMap<String,Integer>();//���ڴ洢���������ȼ���Map  
    //��ʼ�����ȼ�Լ��(�ɸ��ݼ���ĸ��ӳ̶���չ)  
    public CalculatorIn()  
    {  
        priorityMap.put("+",0);  
        priorityMap.put("-",0);  
        priorityMap.put("*", 1);  
        priorityMap.put("/", 1);  
         
    }  
     
    public int getPriority(String op)//�õ�һ�������������ȼ�  
    {  
        return priorityMap.get(op);  
    }  
     
    public boolean highPriority(String op)//�жϲ����������ȼ��ڶ�ջ�����Ƿ���Ϊ��  
    {  
        int opPri=getPriority(op);//��ǰ�����������ȼ�  
        if(!operatorStack.empty())  
        {  
        for(String s:operatorStack)  
        {  
            int priority=getPriority(s);  
            if(opPri<priority)  
                return false;  
             
        }  
        }  
        return true;  
    }  
    //�ѱ��ʽת�����沨��ʽ  
    public String expToIpn()  
    {  
        int index=0;  
        int end=0;  
        String Ipn="";  
        for(int i=0;i<expression.length();i++)  
        {  
            String temps=String.valueOf(expression.charAt(i));  
            if(temps.matches("[0-9.]"))//����Ƿ�������  
            {  
                end++;  
            }  
            else  
            {  
                String tempOperand=expression.substring(index,end);//�õ�������  
                Ipn+=tempOperand+",";  
                String tempOperator=expression.substring(end,++end);//�õ�������  
                if(tempOperator.equals("!"))//���絽���ʽ����󽫲����� ȫ������  
                    {  
                    while(!operatorStack.empty())  
                    {  
                         Ipn+=operatorStack.pop()+",";  
                     }  
                    }  
                 else  
                 {  
                if(highPriority(tempOperator))//���ȼ��ߵ�ѹ���������ջ  
                    {  
                    operatorStack.push(tempOperator);  
                    }  
                 else  
                    {  
                     while(!operatorStack.empty())//  
                    {  
                         Ipn+=operatorStack.pop()+",";  
                     }  
                      
                     operatorStack.push(tempOperator);  
                    }  
                //System.out.println(tempOperand+","+tempOperator);  
                index=end;  
            }  
            }  
             
        }  
        return Ipn;  
     
    }  
    public double calculateIpn(String[] Ipn)//�����沨��ʽ  
    {  
           
        for(int i=0;i<Ipn.length;i++)  
        {  
        //    System.out.println(Ipn[i]);  
            if(Ipn[i].matches("^[0-9]+.?[0-9]*$"))//������ʽ�ж�������  
            {  
                operandStack.push(Double.parseDouble(Ipn[i]));  
            }  
                else  
                {  
                    popOperand(Ipn[i]);  
                }  
        }  
        return result;  
         
    }  
    //����������ʱ��������������������Ӧ������������result  
    public void popOperand(String operator)  
    {  
        double d1=operandStack.pop();  
        double d2=operandStack.pop();  
        System.out.println(d1+operator+d2);  
        if(operator.equals("+"))  
            result=d2+d1;  
        if(operator.equals("-"))  
            result=d2-d1;  
        if(operator.equals("*"))  
            result=d2*d1;  
        if(operator.equals("/"))  
            result=d2/d1;  
//System.out.println(result);  
            operandStack.push(result);  
         
    }  
  
    public Stack getOperandStack() {  
        return operandStack;  
    }  
    public void setOperandStack(Stack operandStack) {  
        this.operandStack = operandStack;  
    }  
    public Stack getOperatorStack() {  
        return operatorStack;  
    }  
    public void setOperatorStack(Stack operatorStack) {  
        this.operatorStack = operatorStack;  
    }  
    public String getexpression_r() {  
        return expression;  
    }  
    public void setexpression_r(String expression) {  
        this.expression = expression;  
    }  
    public double getResult() {  
        return result;  
    }  
    public void setResult(double result) {  
        this.result = result;  
    }  

}
