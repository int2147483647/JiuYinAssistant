package com.fun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dtools.ini.Commentable;
import org.dtools.ini.FormatException;
import org.dtools.ini.IniFile;
import org.dtools.ini.IniItem;
import org.dtools.ini.IniSection;

public class ReadBuff {

    
    public ReadBuff(IniFile ini, File file) {
      //**********************************************************************
        // Step 1 - Check that neither of the arguments are null
        //**********************************************************************
        if( ini == null ) {
            throw new NullPointerException(
                    "The given IniFile cannot be null." );
        }
        
        if( file == null ) {
            throw new NullPointerException( "The given File cannot be null." );
        }
        //**********************************************************************
        // Step 2 - set the fields of this object.
        //**********************************************************************
        
        this.file = file;
        this.ini = ini;
 }
    
    static String getEndLineComment( String line ) {
        
        if( !isSection(line) && !isItem(line) ) {
            throw new FormatException( "getEndLineComment(String) is unable to return the comment from the given string (" +
                    line + ") as it is not an item nor a section." );
        }
        
        int pos = line.indexOf( Commentable.COMMENT_SYMBOL );
        
        if( pos == -1 ) {
            return "";
        }
        else {
            return line.substring( pos+1 ).trim();
        }
    }
    static String getItemName( String line ) {
        
        if( !isItem(line) ) {
            throw new FormatException( "getItemName(String) is unable to " + 
                    "return the name of the item as the given string (" +
                    line + " is not an item." );
        }
        
        // get the index of the first occurrence of the equals sign
        int pos = line.indexOf( '=' );
        
        // no occurrence of equals sign
        if( pos == -1 ) {
            return "";
        }
        else {
            return line.substring( 0, pos ).trim();
        }
    }
    static String getItemValue( String line ) {
        
        if( !isItem(line) ) {
            throw new FormatException( "getItemValue(String) is unable to " + 
                    "return the value of the item as the given string (" +
                    line + ") is not an item." );
        }
        
        //**********************************************************************
        // get the index of the first occurrence of the equals sign and the
        // comment sign
        //**********************************************************************
        int posEquals = line.indexOf( '=' );
        int posComment = line.indexOf( Commentable.COMMENT_SYMBOL );
        
        // no occurrence of equals sign
        if( posEquals == -1 ) {
            
            // no occurence of comment sign
            if( posComment == -1 ) {
                return line;
            }
            else {
                return line.substring(0, posComment ).trim();
            }
        }
        else {
            
            // no occurrence of comment sign
            if( posComment == -1 ) {
                return line.substring(posEquals+1).trim();
            }
            else {
                return line.substring(posEquals+1, posComment).trim();
            }
        }
    }
    static String getSectionName( String line ) {
        
        if( !isSection(line) ) {
            throw new FormatException( "getSectionName(String) is unable to " + 
                    "return the name of the section as the given string (" +
                    line + ") is not a section." );
        }
        
        int firstPos = line.indexOf( '[' );
        int lastPos = line.indexOf( ']' );
        
        return line.substring(firstPos+1, lastPos).trim();
    }
    
    static boolean isComment( String line ) {
        
        line = line.trim();
        
        if( line.isEmpty() ) {
            return false;
        }
        else {
            // if the line is not empty, then return true only if the first
            // character is a comment symbol.
            char firstChar = line.charAt(0);
            return firstChar == Commentable.COMMENT_SYMBOL;
        }
    }
    
    static boolean isItem(String line) {
        
        line = removeComments(line);
        
        if( line.isEmpty() ) {
            return false;
        }
        else {
            int pos = line.indexOf( '=' );
            
            if( pos != -1 ) {
                
                String name = line.substring(0,pos).trim();
                
                return (name.length() > 0);
            }
            else {
                return false;
            }
        }
    }
    static boolean isSection( String line ) {
        
        line = removeComments(line);
        
        if( line.isEmpty() ) {
            return false;
        }
        else {
            char firstChar = line.charAt(0);
            char lastChar  = line.charAt( line.length()-1 );
            
            return firstChar == '[' && lastChar == ']';
        }
    }
    
    static String removeComments( String line ) {
        
        if( line.contains(String.valueOf(Commentable.COMMENT_SYMBOL)) ) {
            return line.substring(0, line.indexOf(Commentable.COMMENT_SYMBOL) ).trim();
        }
        else {
            return line.trim();
        }
    }
    
    private File file;
    
    private IniFile ini;
    
   
    public void read(String coding) throws IOException {
        
        BufferedReader reader;
        String line;
        IniSection currentSection = null;
            
        //**********************************************************************
        // set up reader to read input file
        //**********************************************************************
        reader = new BufferedReader(
              new InputStreamReader(
                      new FileInputStream(file), coding)
        );
        //**********************************************************************
        // process each line of the text file
        //**********************************************************************
        String comment = "";
        Commentable lastCommentable = null;
        int i = 0;
        while( (line = reader.readLine()) != null ) {
            
            //******************************************************************
            // Trim any excess space from the beginning and end of the line
            //******************************************************************
            line = line.trim();
            
            //******************************************************************
            // If the line is empty, go to the next line
            //******************************************************************
            if( line.isEmpty() ) {
                
                //**************************************************************
                // add post comment
                //**************************************************************
                if( !comment.isEmpty() && lastCommentable != null ) {
                    lastCommentable.setPostComment( comment );
                    comment = "";
                }
                i = 0;
                //**************************************************************
                // continue to next line in ini file
                //**************************************************************
                continue;
            }
            //******************************************************************
            // Check to see if it is a comment
            //******************************************************************
            else if( isComment(line) ) {
                
                String tmpComment = line.substring(1).trim();
                
                if( comment.isEmpty() ) {
                    comment = tmpComment;
                }
                else {
                    comment += "/n" + tmpComment;
                }
            }
            
            //******************************************************************
            // if the line is a section, then process it
            //******************************************************************
            else if( isSection(line) ) {
                // get the name of the section from the line
                String sectionName = getSectionName( line );
                
                String endLineComment = getEndLineComment( line );
                long l2=System.nanoTime();
                long l1 = 0,l4=0;
               
                // if section already exists, then get section
                if( ini.hasSection(sectionName) ) {
                    currentSection = ini.getSection( sectionName );
                }
                else {
                	
                    // section doesn't already exists
                    // create a new instance of a section
                	/*if (CommonUtils.isContainChinese(sectionName)) {
						continue;
					}*/
                    try {
						currentSection = ini.addSection( sectionName );
					} catch (Exception e) {
					}
                }
                /* set the end line comment of the section
                 * 
                 * NOTE: this may replace any previous end line comment if
                 * the section had already existed.
                 */
                currentSection.setEndLineComment( endLineComment );
                
                //**************************************************************
                // add pre comment
                //**************************************************************
                if( !comment.isEmpty() ) {
                    currentSection.setPreComment( comment );
                    comment = "";
                }
                
                //**************************************************************
                // keep a reference of the latest item so that post comments can
                // be added to it later
                //**************************************************************
                lastCommentable = currentSection;
            }
            //******************************************************************
            // If the line is an item, then process the item
            //******************************************************************
            else if( isItem(line) ) {
                
                //**************************************************************
                // Check that a section has already been read
                //**************************************************************
                if( currentSection == null ) {
                    throw new FormatException( "An Item has been read," +
                            "before any section." );
                }
                
                //**************************************************************
                // get name, value and end line comments of the item
                //**************************************************************
                String itemName = getItemName( line );
                String itemValue = getItemValue( line );
                String endLineComment = getEndLineComment( line );
                
                IniItem item;
                
                // if the current section already has an item with same name
                if( currentSection.hasItem(itemName) ) {
                    //item = currentSection.getItem( itemName );
                    item = currentSection.addItem( itemName +i);
                    i++;
                }
                else {
                	if (CommonUtils.isContainChinese(itemName)) {
						continue;
					}
                    
                    item = currentSection.addItem( itemName );
                }
                //**************************************************************
                // add value and end line comments
                //**************************************************************
                item.setValue( itemValue );
                item.setEndLineComment( endLineComment );
                
                //**************************************************************
                // add pre comment
                //**************************************************************
                if( !comment.isEmpty() ) {
                    item.setPreComment( comment );
                    comment = "";
                }
                
                //**************************************************************
                // keep a reference of the latest item so that post comments can
                // be added to it later
                //**************************************************************
                lastCommentable = item;
            }
            
            //System.out.println(">>>>>    "+(l2-l1));
        } // end reading file
        //**********************************************************************
        // if there is comment still unprocessed, then add post comment
        //**********************************************************************
        if( !comment.isEmpty() && lastCommentable != null ) {
            lastCommentable.setPostComment( comment );
            comment = "";
        }
        reader.close();
        
    } // end method read()
}

