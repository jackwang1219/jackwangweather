/**
  * Copyright 2018 bejson.com 
  */
package com.example.jackwang_0303.myapplication;
import java.util.List;

/**
 * Auto-generated: 2018-07-24 15:59:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class VideoBean {

    private int count;
    private List<Items> items;
    private int total;
    private int page;
    private int err;
    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setPage(int page) {
         this.page = page;
     }
     public int getPage() {
         return page;
     }

    public void setErr(int err) {
         this.err = err;
     }
     public int getErr() {
         return err;
     }

}