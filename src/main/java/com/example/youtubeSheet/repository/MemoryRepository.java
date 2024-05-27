package com.example.youtubeSheet.repository;

import com.example.youtubeSheet.entity.Sheet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoryRepository {
    static Map<Sheet,Long> repository=new HashMap<>();

    private static MemoryRepository instance;
    static Long id= 0L;

    public MemoryRepository() {
    }

    public static Map<Sheet, Long> getRepository() {
        return repository;
    }

    public static MemoryRepository getInstance(){
        if(instance==null){
            instance=new MemoryRepository();
        }
        return instance;
    }

    public void save(Sheet sheet){
        sheet.setId(++id);
        repository.put(sheet,id);
        System.out.println(id+" "+sheet.getTitle());

    }

    public String show(Long id){
        String url="";

        for(Sheet sheet:repository.keySet()){
            if(repository.get(sheet).equals(id)){
                url=sheet.getUrl();
            }
        }
        return url;
    }
    //key값
    public List<Sheet> sheetList(){
        List<Sheet> sheetList=new ArrayList<>();
        repository.keySet().forEach(key->sheetList.add(key));
        return sheetList;
    }


    public void delete(Long id){
        for(Sheet sheet:repository.keySet()){
            if(repository.get(sheet).equals(id)){
                repository.remove(sheet);
            }
        }
    }

    public void modify(Long id,String title){
        for(Sheet sheet:repository.keySet()){
            if(repository.get(sheet).equals(id)){
                sheet.setTitle(title);
            }
        }
    }
}
