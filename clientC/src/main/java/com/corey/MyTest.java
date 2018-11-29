package com.corey;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/23</li>\n
 */

public class MyTest extends  Thread{
    private Integer i=0;

    @Override
    public void run(){
        for(int a=0;a<10;a++){
            System.out.println(Thread.currentThread().getName()+":"+i);
            try {
                Thread.sleep( 100l );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            i=++i;
        }
    }
}
