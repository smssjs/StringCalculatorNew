

import com.StackInheritance;
import com.StackInheritance1;
import com.EmptyListException;
//Diya added comment
// Java core packages
// Anand added comment
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
//import Java.util.StringTokenizer;
//import java.util*;
// Java extension packages
import javax.swing.*;

public class StringCalculator extends JFrame
   implements ActionListener 
{

   private double inputBuffer1 =  0, finalResult = 0;
   private JButton bOne, bTwo, bThree, bFour, bFive,
                   bSix, bSeven, bEight, bNine,  bZero, bExit,
                   bMult, bDiv, bSub, bPlus, bEquals, bHelp,
                   bAbout,  bDecPt, bClear, bLeftBrace, bRightBrace ;
   private JTextField inputField1, inputField2, numericField, outputField;
   private int stack1Memory = 0,pushCounter = 1;
   private Font  font;
   private String firstNum = new String(""), secondNum  = new String("");
   private boolean myDiag = false, result = true ,
                   firstNumSet = false, secondNumSet = false,
                   pendingOp  = false, reStart = true ;
   private double aNum, dNum1 = 0.0, dNum2 = 0.0 , answer = 0.0,
                   tempD = 0.0 , minusOne = -1.0 ;
   
   double[] inputBuffer2  = new double[22];
  
    StackInheritance stack = new StackInheritance();  
    StackInheritance1 stack1 = new StackInheritance1();  
   
    Object removedObject = null;
   
    // set up GUI
   public StringCalculator()
   {
      super( "Expert Calculator Exception" );

      // get content pane and set its layout
      	Container container = getContentPane();
      	container.setLayout( new GridLayout( 5, 4 ) );
		JLabel label = new JLabel();
        
        //getContentPane().add(label,BorderLayout.NORTH);

	   numericField = new JTextField( 20 );
		
		
      // set up label and inputField1
      container.add( new JLabel( "Your Infix formular is :", SwingConstants.RIGHT ) );
      inputField1 = new JTextField( 20 );
      container.add( inputField1 );
      
      // set up label and inputField2; register listener
      container.add( new JLabel( "Postfix expression is :", SwingConstants.RIGHT ) );
      inputField2 = new JTextField( 20 );
      container.add( inputField2 );
   
      // set up label and outputField
      container.add( new JLabel( "RESULT ", SwingConstants.RIGHT ) );
      outputField = new JTextField();
      container.add( outputField );
      
     
      /** setup button Panel and the number buttons */
      setUpButtons() ;
      /** setup function panel and its buttons*/
      setUpFunctionPanel() ;
      
      
      /** setup exit panel and its buttons */
      setUpExitPanel() ;

      setUpActionListener() ;
      
      setSize( 300, 450 );
      setVisible( true );
      show();
      
   }
 
 
  
  public void setUpButtons() {
      JPanel buttonPanel = new JPanel() ;
      getContentPane().add( buttonPanel , BorderLayout.CENTER) ;
      //buttonPanel.add(new JLabel("Click to input", SwingConstants.RIGHT ));
     
      
      //bZero.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bZero   = new JButton( "0" ) ;
      bOne    = new JButton( "1" ) ;
      bTwo    = new JButton( "2" ) ;
      bThree  = new JButton( "3" ) ;
      bFour   = new JButton( "4" ) ;
      bFive   = new JButton( "5" ) ;
      bSix    = new JButton( "6" ) ;
      bSeven  = new JButton( "7" ) ;
      bEight  = new JButton( "8" ) ;
      bNine   = new JButton( "9" ) ;
      bExit   = new JButton( "Exit" ) ;
      bClear   = new JButton( "Clear" ) ;
      bClear.setBackground( Color.blue ) ;
      bClear.setForeground( Color.white );
      bMult   = new JButton( "*" ) ;
      bMult.setFont( new Font("Sanserif", Font.BOLD, 20 ) );
      bMult.setBackground( Color.blue ) ;
      bMult.setForeground( Color.white );
      bDiv    = new JButton( "/" ) ;
      bDiv.setFont( new Font("Sanserif", Font.BOLD, 20 ) );
      bDiv.setBackground( Color.blue ) ;
      bDiv.setForeground( Color.white );
      bSub    = new JButton( "-" ) ;
      bSub.setFont( new Font("Sanserif", Font.BOLD, 24 ) );
      bSub.setBackground( Color.blue ) ;
      bSub.setForeground( Color.white );
      bPlus   = new JButton( "+" ) ;
      bPlus.setBackground( Color.blue ) ;
      bPlus.setForeground( Color.white );

      bEquals = new JButton( "=" ) ;
      bEquals.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bEquals.setBackground( Color.red ) ;
      bEquals.setForeground( Color.white );
      bDecPt = new JButton( "." ) ;
      bDecPt.setFont( new Font("Sanserif", Font.BOLD, 22 ) );
      
      bLeftBrace = new JButton( "(" ) ;
      bLeftBrace.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bLeftBrace.setBackground( Color.blue ) ;
      bLeftBrace.setForeground( Color.white );
      bRightBrace = new JButton( ")" ) ;
      bRightBrace.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bRightBrace.setBackground( Color.blue ) ;
      bRightBrace.setForeground( Color.white );

      /** add the following buttons to the buttonPanel panel. */


      buttonPanel.add( bSeven ) ;
      buttonPanel.add( bEight ) ;
      buttonPanel.add( bNine ) ;

      buttonPanel.add( bFour ) ;
      buttonPanel.add( bFive ) ;
      buttonPanel.add( bSix ) ;
      buttonPanel.add( bOne ) ;
      buttonPanel.add( bTwo ) ;
      buttonPanel.add( bThree ) ;
      buttonPanel.add( bZero ) ;
      buttonPanel.add( bDecPt ) ;
      buttonPanel.add( bEquals ) ;
      buttonPanel.setLayout( new GridLayout( 4, 3, 5, 5  ) );
   }


  public void setUpActionListener()  {
     
      bDecPt.addActionListener( this );
      bZero.addActionListener( this );
      bOne.addActionListener( this );
      bTwo.addActionListener( this );
      bThree.addActionListener( this );
      bFour.addActionListener( this );
      bFive.addActionListener( this );
      bSix.addActionListener( this );
      bSeven.addActionListener( this );
      bEight.addActionListener( this );
      bNine.addActionListener( this );
      bExit.addActionListener( this );
      bMult.addActionListener( this );
      bDiv.addActionListener( this );
      bSub.addActionListener( this );
      bPlus.addActionListener( this );
      bEquals.addActionListener( this );
      bRightBrace.addActionListener(this);
      bLeftBrace.addActionListener(this);
      bClear.addActionListener( this );
      bExit.addActionListener( this );
   }

   public void setUpFunctionPanel()
   {
  
      JPanel functionPanel = new JPanel();
      getContentPane().add( functionPanel , BorderLayout.CENTER ) ;
      functionPanel.setLayout( new GridLayout( 4, 3, 5, 5  ) );
      functionPanel.add( bMult ) ;
      functionPanel.add( bDiv ) ;
      functionPanel.add( bSub ) ;
      functionPanel.add( bPlus ) ;
      functionPanel.add( bLeftBrace );
      functionPanel.add( bRightBrace ) ;
      functionPanel.add( bClear ) ;
   }

    public void setUpExitPanel()
    {
      JPanel exitPanel = new JPanel() ;
      getContentPane().add( exitPanel , BorderLayout.SOUTH ) ;
      bExit   = new JButton( "Exit" ) ;
      bExit.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bExit.setBackground( Color.blue ) ;
      bExit.setForeground( Color.white );
     
      exitPanel.add( bExit ) ;
    }

    public void actionPerformed( ActionEvent e )
    {
      if ( result ) 
      {
         MyPrint( "The value of result is " + result );
         result = false ;
         outputField.setText( "" );
      }
      if( stack1Memory >= 19)
      {
      	JOptionPane.showMessageDialog( this, "You have not entered more than 20 numbers of operation." ,
                     "Over operation entered",
                        JOptionPane.ERROR_MESSAGE );
      }
      else if( e.getSource() == bDecPt ) 
      {
       		if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
         		MyPrint( "The decimal point button was pressed." );
         		inputField1.setText( inputField1.getText() + "." ) ;
         		numericField.setText( numericField.getText() + "." ) ;
         		firstNumSet = true ;
         		pendingOp = true;
         	}
      }
      else if( e.getSource() == bZero ) 
      {
      		if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
      
         		MyPrint( "The zero button was pressed. And pendingOp is " + pendingOp );
        	 	inputField1.setText( inputField1.getText() + "0" ) ;
        		 numericField.setText( numericField.getText() + "0" ) ;
         		firstNumSet = true ;
         		pendingOp = true;
         	}
      }
      else  if ( e.getSource() == bOne )
      {
      	if( !reStart ) 
         {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
               "Buffer is not blank",
               JOptionPane.ERROR_MESSAGE );
         }
         else  
      	 {
               MyPrint( "The one button was pressed. And pendingOp is " + pendingOp  );
         	inputField1.setText( inputField1.getText() + "1" ) ;
         	numericField.setText( numericField.getText() + "1" ) ;
         	firstNumSet = true ;
         	pendingOp = true;
         }
      }
      else  if ( e.getSource() == bTwo ) 
      {
      	    if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
      
       			  	MyPrint( "The two button was pressed. And pendingOp is " + pendingOp );
         			inputField1.setText( inputField1.getText() + "2" ) ;
         			numericField.setText( numericField.getText() + "2" ) ;
         			firstNumSet = true ;
         			pendingOp = true;
         	}
      }
      else  if ( e.getSource() == bThree ) 
      {
       		if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
      
        		 MyPrint( "The Three button was pressed. And pendingOp is " + pendingOp);
         		inputField1.setText( inputField1.getText() + "3" ) ;
         		numericField.setText( numericField.getText() + "3" ) ;
         		firstNumSet = true ;
         		pendingOp = true;
         	}
      }
      else  if ( e.getSource() == bFour ) 
      {
       		if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
     
         		MyPrint( "The Four button was pressed. And pendingOp is " + pendingOp );
        		 inputField1.setText( inputField1.getText() + "4" ) ;
         		numericField.setText( numericField.getText() + "4" ) ;
         		firstNumSet = true ;
         		pendingOp = true;
         	}
      }
      else  if ( e.getSource() == bFive ) 
      {
       if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
		         MyPrint( "The Five button was pressed. And pendingOp is " + pendingOp );
  		       	inputField1.setText( inputField1.getText() + "5" ) ;
   		      	numericField.setText( numericField.getText() + "5" ) ;
    		     firstNumSet = true ;
       		 	 pendingOp = true;
     	    }
      }
      else  if ( e.getSource() == bSix ) 
      {
       if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
         		MyPrint( "The Six button was pressed. And pendingOp is " + pendingOp );
         		inputField1.setText( inputField1.getText() + "6" ) ;
         		numericField.setText( numericField.getText() + "6" ) ;
         		firstNumSet = true ;
         		pendingOp = true;
         	}
      }
      else  if ( e.getSource() == bSeven ) 
      {
      
      
       if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
		         MyPrint( "The Seven button was pressed. And pendingOp is " + pendingOp );
    		     inputField1.setText( inputField1.getText() + "7" ) ;
     		    numericField.setText( numericField.getText() + "7" ) ;
      		   firstNumSet = true ;
      			   pendingOp = true;
         	}
      }
      else  if ( e.getSource() == bEight ) 
      {
      
      
       if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
 
        		 MyPrint( "The Eight button was pressed. And pendingOp is " + pendingOp );
      		   inputField1.setText( inputField1.getText() + "8" ) ;
      		   numericField.setText( numericField.getText() + "8" ) ;
       		  firstNumSet = true ;
       		  pendingOp = true;
       	  }
      }
      else  if ( e.getSource() == bNine ) 
      {
       if( !reStart ) 
            {
               JOptionPane.showMessageDialog( this, "Please press clear to continue" ,
                     "Buffer is not blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
      		{
      
  		       MyPrint( "The Nine button was pressed. And pendingOp is " + pendingOp );
    		     inputField1.setText( inputField1.getText() + "9" ) ;
    		     numericField.setText( numericField.getText() + "9" ) ;
     		    firstNumSet = true ;
      		   pendingOp = true;
       	  }
      }
      else  if( e.getSource() == bExit ) 
      {
         MyPrint( "The Exit button was pressed." );
         sysExit() ;
      }
      else  if( e.getSource() == bLeftBrace ) 
      {
      	 if( pendingOp )
      	 {
      	 	pushStack1();
       	 }
 
         inputField1.setText( inputField1.getText() + "(" ) ;
         stack.push( "(" );
		 if(myDiag)stack.print();
		 pushCounter ++;
      }
      else  if( e.getSource() == bRightBrace ) 
      {
       	    if( !getFirstNum() || pushCounter < 2 ) 
            {
               JOptionPane.showMessageDialog( this, "You have not entered a first number." ,
                     "First number blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else  
            {
             	if( pendingOp )
      	 		{
      	 			pushStack1();
       	 		}
            
            
            
              	do
      			{
                         
      		   		if( pushCounter > 2)
                   	{
      					if((stack1Memory >= 19)||(pushCounter <= 0))
      					{
                          // stack.push( "(" );
      			   		  // if(myDiag)stack.print();
      			   		//pushCounter ++;
      			   		break;
      				}
      				removedObject = stack.pop();
      				pushCounter --;
      				if(removedObject.equals("("))break;
					stack1.push1( removedObject );
					if(myDiag)stack1.print();
      				stack1Memory ++;
      		   	}
      		   else 
      		  	{
                        //System.out.print("debug1"+ input);
      				stack.push( ")" );
      				if(myDiag)stack.print();
      				pushCounter ++;
      				break;
      		  	} 	
      	
      		}while(true);
      		
      		inputField1.setText( inputField1.getText() + ")" ) ;
      	   }
      }
      else  if( e.getSource() == bMult ) 
      {
        	if( !getFirstNum() ) 
            {
               JOptionPane.showMessageDialog( this, "You have not entered a first number." ,
                     "First number blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else
            {
             	if( pendingOp )
      	 		{
      	 			pushStack1();
       	 		}
            
            	do
            	{
            		if( pushCounter > 1)
            		{
              			removedObject = stack.pop();
             	   		pushCounter --;
                		if(( removedObject.equals("*") )
                		&&( stack1Memory != 0))
                		{
                  			stack1.push1( removedObject );
                   			if(myDiag)stack1.print();
                    		stack1Memory ++;
                   		}
                		else if(stack1Memory >= 0)
                		{
      						stack.push(removedObject);
      						if(myDiag)stack.print();
      						pushCounter ++;
      						stack.push( "*" );
      						if(myDiag)stack.print();
      						pushCounter ++;
      						break;
      					}
                
            		}
            		else
            		{
  						stack.push( "*" );
  						if(myDiag)stack.print();
  						pushCounter ++;
  						break;
   					} 
           		}while(true);	
               	inputField1.setText( inputField1.getText() + "*" ) ;
            	MyPrint( "The Mult button was pressed. And pendingOp is " + pendingOp  );
            }
      }
      else  if ( e.getSource() == bDiv ) 
      {
       		if( !getFirstNum()  ) 
            {
               JOptionPane.showMessageDialog( this, "You have not entered a first number." ,
                     "First number blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else
            {
            	if( pendingOp )
      	 		{
      	 			pushStack1();
       	 		}
 
            	do
            	{
            		if( pushCounter > 1)
            		{
              			removedObject = stack.pop();
             	   		pushCounter --;
                		if(( removedObject.equals("*") )
                		||(removedObject.equals("/"))
      				&&( stack1Memory != 0))
                		{
                  			stack1.push1( removedObject );
                   			if(myDiag)stack1.print();
                    		stack1Memory ++;
                   		}
                		else if(stack1Memory >= 0)
                		{
      						stack.push(removedObject);
      						if(myDiag)stack.print();
      						pushCounter ++;
      						stack.push( "/" );
      						if(myDiag)stack.print();
      						pushCounter ++;
      						break;
      					}
            		}
            		else
            		{
  						stack.push( "/" );
  						if(myDiag)stack.print();
  						pushCounter ++;
  						break;
   					} 
           		}while(true);	
       			inputField1.setText( inputField1.getText() + "/" ) ;
       			MyPrint( "The Div button was pressed. And pendingOp is " + pendingOp  );
      		}
      }
      else  if ( e.getSource() == bSub ) 
      {
       		if( !getFirstNum()  ) 
            {
               JOptionPane.showMessageDialog( this, "You have not entered a first number." ,
                     "First number blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else
            {
            	if( pendingOp )
      	 		{
      	 			pushStack1();
       	 		}
            
            
            
            
                do
            	{
            		if( pushCounter > 1)
            		{
              			removedObject = stack.pop();
             	   		pushCounter --;
                		if(( removedObject.equals("*") )
                		||(removedObject.equals("/"))
                		||(removedObject.equals("+"))
                		||(removedObject.equals("-"))
      				&&( stack1Memory != 0))
                		{
                  			stack1.push1( removedObject );
                   			if(myDiag)stack1.print();
                    		stack1Memory ++;
                   		}
                		else if(stack1Memory >= 0)
                		{
      						stack.push(removedObject);
      						if(myDiag)stack.print();
      						pushCounter ++;
      						stack.push( "-" );
      						if(myDiag)stack.print();
      						pushCounter ++;
      						break;
      					}
                
            		}
            		else
            		{
  						stack.push( "-" );
  						if(myDiag)stack.print();
  						pushCounter ++;
  						break;
   					} 
    	       	}while(true);	
	           	MyPrint( "The Sub button was pressed. And pendingOp is " + pendingOp  );
            	inputField1.setText( inputField1.getText() + "-" ) ;
      		}
      }
      else  if ( e.getSource() == bPlus ) 
      {
    		if( !getFirstNum()  ) 
            {
               JOptionPane.showMessageDialog( this, "You have not entered a first number." ,
                     "First number blank",
                        JOptionPane.ERROR_MESSAGE );
            }
            else
            {
            	if( pendingOp )
      	 		{
      	 			pushStack1();
       	 		}
            
            
                do
            	{
            		if( pushCounter > 1)
            		{
              			removedObject = stack.pop();
             	   		pushCounter --;
                		if(( removedObject.equals("*") )
                		||(removedObject.equals("/"))
                		||(removedObject.equals("+"))
      					&&( stack1Memory != 0))
                		{
                  			stack1.push1( removedObject );
                   			if(myDiag)stack1.print();
                    		stack1Memory ++;
                   		}
                		else if(stack1Memory >= 0)
                		{
      						stack.push(removedObject);
      						if(myDiag)stack.print();
      						pushCounter ++;
      						stack.push( "+" );
      						if(myDiag)stack.print();
      						pushCounter ++;
      						break;
      					}
                
            		}
            		else
            		{
  						stack.push( "+" );
  						if(myDiag)stack.print();
  						pushCounter ++;
  						break;
   					} 
    	       	}while(true);	
               	MyPrint( "The Plus button was pressed. And pendingOp is " + pendingOp  );
            	inputField1.setText( inputField1.getText() + "+" ) ;
      		}
      }
      else  if ( e.getSource() == bEquals ) 
      {
      	if( pendingOp )
      	{
      		pushStack1();
       	}
      
      	 //pushCounter --;
      	 
      	 
      	 
         //MyPrint( "The Equals button was pressed. And pendingOp is " + pendingOp  +
           //  " The opCode value is " + opCode );

         if ( inputField1.getText().equals( "" )  || !firstNumSet  ) 
         {
            JOptionPane.showMessageDialog( this, "Equals a: You have not entered a first number." ,
                     "First number blank",
                        JOptionPane.ERROR_MESSAGE );
         }
        /**
         else if( !pendingOp ) 
         {
            inputField1.setText( "" ) ;
            JOptionPane.showMessageDialog( this, "Equals b: You have not entered a first number and/or an operation." ,
                     "No operation entered",
                        JOptionPane.ERROR_MESSAGE );
         }**/
         else 
         {
            opCodeMethod() ;
         }
			setResults();
         //firstNum = "" + answer ;
         firstNumSet = false ;
         reStart = false;
      }
      else  if ( e.getSource() == bClear ) 
      {
         MyPrint( "The Clear button was pressed." );
         firstNum = ""  ;
         secondNum = ""  ;
         firstNumSet = false ;
         inputField1.setText("") ;
         inputField2.setText("") ;
         numericField.setText("");
         outputField.setText("");
         stack1Memory = 0;
         pushCounter = 1;
         result  = false;
      	 pendingOp = false ;
      	 stack.isEmpty();
      	 stack1.isEmpty();
         reStart = true;
         
         
      }
   }

	private void pushStack1()
	{
		stack1.push1(numericField.getText());
        if(myDiag)stack1.print();
        stack1Memory ++;
        pendingOp = false;
        numericField.setText("");
	}



  /** opCodeMethod() */
   private void opCodeMethod() 
   {
     

      //MyPrint( "In opCodeMethod() with an opCode of " + opCode );

     //System.out.println("pushCount: start "+ pushCounter);
     do
     {
      		
      	if(pushCounter > 1)
      	{
                    
      	    if((stack1Memory >= 19)||(pushCounter <= 0))
      	    {
      		break;
      	    }
             if(myDiag)stack.print();
             if(myDiag)stack1.print();
      	     removedObject = stack.pop();
             if(myDiag)stack.print();
             //System.out.println(removedObject.toString() + "popped");
             pushCounter --;
             //System.out.println("pop result1  "+ pushCounter);
                  
              stack1.push1( removedObject );
              if(myDiag)stack1.print();
      	      stack1Memory ++;
              //System.out.println("pop result2  "+ pushCounter);
                     
      	}
      	else
      	{
                
            //System.out.println("pushCount: END2 "+ pushCounter);
      		break;
      	}
      }while(true);
      
      
	//Evaluating Postfix Expression
    //A stack is also being used to evaluated postfix expression, i.e. to calculate the value produces by the postfix expression.
	inputField2.setText( "" ) ;
    for(int count = 0; count < stack1Memory; count++)
    {
       removedObject = stack1.pop1();
       if(myDiag)stack1.print();
       stack.push(removedObject);
       if(myDiag)stack.print();
       inputField2.setText( inputField2.getText() + removedObject ) ;
       inputField2.setText( inputField2.getText() + " " ) ;
    }
    
    finalResult = 0;
    //System.out.println("stack1Memory "+ stack1Memory);
    pushCounter = stack1Memory;
	int arrayCount = 0;
	for(int count = 0; count < pushCounter; count++)
	{
		if( stack1Memory < 0)
		{
			//System.out.println(  " popped"+ stack1Memory);
			break;
		}
             
        
        removedObject = stack.pop();
        if(myDiag)stack.print();
       
                
		if( removedObject.toString().equals("*"))
		{
			
			  if(arrayCount <= 0)
              {
                     arrayCount = 0;
                     finalResult = inputBuffer2[arrayCount];
              }
              else
              {
                    arrayCount--;
                    finalResult = (inputBuffer2[arrayCount] * inputBuffer2[arrayCount-1]);
                    inputBuffer2[arrayCount -1 ] = finalResult;
              }
            
            //System.out.println( " finalResult: " + finalResult );
            		
			
		}
		else if( removedObject.toString().equals("/"))
		{
			
               if(arrayCount <= 0)
               {
                   arrayCount = 0;
                 	 finalResult = inputBuffer2[arrayCount];
               }
               else
               {
                    arrayCount--;
                     finalResult = (inputBuffer2[arrayCount - 1] / inputBuffer2[arrayCount]);
                     inputBuffer2[arrayCount -1 ] = finalResult;
                     
               }
                                 
			//System.out.println( " finalResult: " + finalResult );
            		
		}
		else if( removedObject.toString().equals("+"))
		{
		 
          
               if(arrayCount <= 0)
               {
                    arrayCount = 0;
                 	 finalResult = inputBuffer2[arrayCount];
               }
               else
               {
                    arrayCount--;
                     finalResult = (inputBuffer2[arrayCount] + inputBuffer2[arrayCount-1]);
                     inputBuffer2[arrayCount -1 ] = finalResult;
                      
               }
                                 
			//System.out.println( " finalResult: " + finalResult );
            		
		}
		else if( removedObject.toString().equals("-"))
		{
   		       if(arrayCount <= 0)
               {
                    arrayCount = 0;
                 	 finalResult = inputBuffer2[arrayCount];
               }
               else
               {
                    arrayCount--;
                     finalResult = (inputBuffer2[arrayCount - 1] - inputBuffer2[arrayCount]);
                     inputBuffer2[arrayCount -1 ] = finalResult;
                    
               }
               
		       //System.out.println( " finalResult: " + inputBuffer2[arrayCount] + "arrayCount:" + arrayCount);
            		
		}
		else if(( removedObject.toString().equals(""))
              ||( removedObject.toString().equals("=")))
		{
			if(myDiag)stack.print();
            if(myDiag)stack1.print();
			break;
		}
        else
        {
	   	   inputBuffer1 = Double.parseDouble(removedObject.toString());
           inputBuffer2[arrayCount] = inputBuffer1;
           //System.out.println( " finalResult: " + inputBuffer2[arrayCount] + "arrayCount:" + arrayCount);
           arrayCount++;
           //System.out.println( " finalResult: " + inputBuffer2[arrayCount]+ "arrayCount" + arrayCount);
           //stack1.push1(removedObject);
           //stack1.print();
        }
	
	}
	
	finalResult = inputBuffer2[0];
	answer = finalResult;
    //outputField.setText( finalResult ) ;
     // catch exception if stack empty when item popped
     //catch ( EmptyListException emptyListException ) 
     //{
       //  emptyListException.printStackTrace();
      

   } /** ********** End of opCodeMethod() **************/


    /** ***** End of actionPerformed() ****************/
	/**  sysExit() */
   private void sysExit()
   {
      System.exit( 0 );

   } 

/**  MyPrint() */
   private void MyPrint( String str )   
   {
      if ( myDiag )
         System.out.println( str );
   } /** ********** End of MyPrint() **************/


/** getFirstNum() */
   private boolean getFirstNum( )
   {
      boolean retCode = false;

      if (  !firstNumSet  )  
      {
         if(  !inputField1.getText().equals( "" ) )  
         {
            firstNum = inputField1.getText();
            firstNumSet = true ;
            result = true ;
            retCode = true ;
           
         }
         else if (  inputField1.getText().equals( "" ) ) 
         {
            //MyPrint( "1b- In checkFirstNum( ) firstNumSet is " + inputField1.getText() );
            JOptionPane.showMessageDialog( this, "The first number cannot be blank." ,
                     "Invalid Number",
                        JOptionPane.ERROR_MESSAGE );

            retCode = false ;
         }
      }
      else if ( firstNumSet ) 
      {
         firstNumSet = true ;
         result = true ;
         retCode = true ;
      }
      else if ( inputField1.getText().equals( "" ) ) 
      {
         //MyPrint( "1c- In checkFirstNum( ) firstNumSet is " + inputField1.getText() );
         JOptionPane.showMessageDialog( this, "The first number =s " +
                 inputField1.getText() + " cannot be blank." ,
                     "Invalid Number",
                        JOptionPane.ERROR_MESSAGE );

         retCode = false ;
      }
 	  //MyPrint( "1a- In checkFirstNum( ) -- firstNum =s " + firstNum);
      //MyPrint( "1d- In checkFirstNum( ): retCode is " + retCode ) ;

      return retCode ;
   } /** ***** End of checkFirstNum() ****************/

   private void setResults() 
   {
      outputField.setText( "" + answer );
      firstNum = "" + answer ;
      firstNumSet = true ;
      result  = true ;
      pendingOp = false ;
      stack.isEmpty();
      stack1.isEmpty();
   } /** ***** End of checkFirstNum() ****************/


/** convertNumsToDouble() */
   private void convertNumsToDouble() 
   {
      if ( !firstNum.equals( "" ) )
         dNum1 = Double.parseDouble( firstNum ) ;
      if ( !secondNum.equals( "" ) )
         dNum2 = Double.parseDouble( secondNum ) ;
      //MyPrint( "convertNumsToDouble(): The value of dNum1 is " + dNum1 );
      //MyPrint( "convertNumsToDouble(): The value of dNum2 is " + dNum2 );

   }
   
   
   
/** ********** End of convertNumsToDouble() **************/



   // execute application
   public static void main( String args[] )
   {
        StringCalculator application = new StringCalculator();

      application.setDefaultCloseOperation(
         JFrame.EXIT_ON_CLOSE );
   }
   
 

}  
