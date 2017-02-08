package com.example.philip.werwaffle.netcode2;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by philip on 1/27/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetAddress;
import java.util.ArrayList;


public class WifiHelper {
    public static ArrayList<String> deviceList;

    public static ArrayList<String> getDeviceList() {
        if (deviceList == null) {
            deviceList = new ArrayList();
        }
        BufferedReader br;
        boolean isFirstLine = true;

        try {
            deviceList.clear();
            br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] splitted = line.split(" +");
                if (splitted.length >= 4) {
                    String macAddress = splitted[3];
                    String ipAdress = splitted[0];
                    //String containment = macAddress +","+ipAdress;
                    if (deviceList.contains(ipAdress)){}else{
                        deviceList.add(ipAdress);}
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }
}
