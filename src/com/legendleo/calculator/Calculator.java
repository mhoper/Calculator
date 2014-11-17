package com.legendleo.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * 算法还是有点问题，结果为科学记数法后再运算结果就会出错
 * @author legendleo
 *
 */
public class Calculator {
	 private List<String> list = new ArrayList<String>();   
	    private Stack<String> stack = new Stack<String>();   
	       
	       
	    private List<String> resolveExpr(String exp){      
	        //String opert=exp.replaceAll("\\d*\\.\\d+?", "");
	    	String opert=exp;
	        List<String> list=new ArrayList<String>();      
	        int pidx=0; 
	        for(int i=0;i<opert.length();i++){      
	            String p=opert.substring(i, i+1);
	            
	            if(p.matches("[0-9.E]"))//检查是否是数字、包括科学计数法中的E
	            {  
	                pidx++;
	            }else{
	            
		            //pidx=exp.indexOf(p);
		            if(exp.substring(0,pidx).trim().length()!=0){      
		                list.add(exp.substring(0, pidx));      
		            }     
		            list.add(exp.substring(pidx, pidx+1));      
		            exp=exp.substring(pidx+1);
		            pidx = 0;
	            }
	            
	        }  
	        if(exp.length()>0){      
	            list.add(exp);      
	        }      
	        return list;      
	    }      
	       
	    private void dealSign(String s){   
	        if(stack.size()==0){   
	            stack.push(s);   
	            return;   
	        }   
	        String ps = stack.pop();   
	        if(Op.compare(s, ps)>0||ps.equals("(")){   
	            if(s.equals(")")){   
	                list.add(ps);   
	                while(stack.size()>0){   
	                    ps = stack.pop();   
	                    if(ps.equals("("))   
	                        break;   
	                    list.add(ps);   
	                }   
	            }else{   
	                stack.push(ps);   
	                stack.push(s);   
	            }   
	        }else{   
	            list.add(ps);   
	            dealSign(s);   
	        }   
	    }   
	       
	    private void dealVar(String s){   
	        list.add(s);   
	    }   
	       
	    private Double getResult(){   
	        for(String s:list){   
	            if(!Op.isSign(s)){   
	                stack.push(s);   
	                continue;   
	            }   
	            Object a = 0,b = 0;   
	            if(stack.size()>0)   
	                b = stack.pop();   
	            if(stack.size()>0)   
	                a = stack.pop();   
	            stack.push(Op.cal(a, b, s)+"");   
	        }   
	        return Double.valueOf(stack.pop());
	    }   
	       
	    public Double calculate(String expression){   
	        List<String> ss = resolveExpr(expression);   
	        for(String s:ss){   
	            if(Op.isSign(s)){   
	                dealSign(s);   
	            }else{   
	                dealVar(s);   
	            }   
	        }   
	        while(stack.size()>0){   
	            list.add(stack.pop());   
	        }   
	        System.out.println(list);
	           
	        return getResult();   
	    }   
}
