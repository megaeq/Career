package com.test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class TestWatcherService
{
	private WatchService watcher;     
    public TestWatcherService(Path path)throws IOException{     
        watcher = FileSystems.getDefault().newWatchService();     
        path.register(watcher, ENTRY_CREATE,ENTRY_DELETE,ENTRY_MODIFY);     
    }     
         
    public void handleEvents() throws InterruptedException{     
        while(true){     
            WatchKey key = watcher.take();     
            for(WatchEvent event : key.pollEvents()){     
                WatchEvent.Kind kind = event.kind();     
                     
                if(kind == OVERFLOW){//事件可能lost or discarded     
                    continue;     
                }     
                     
                WatchEvent e = (WatchEvent)event;     
                Path fileName = (Path)e.context();     
                     
                System.out.printf("Event %s has happened,which fileName is %s%n"    
                        ,kind.name(),fileName);     
            }     
            if(!key.reset()){     
                break;     
            }     
        }     
    }     
         
    public static void main(String args[]) throws IOException, InterruptedException{     
        String path = "C:\\jenkins"; 
        new TestWatcherService(Paths.get(path)).handleEvents();     
    } 
}
