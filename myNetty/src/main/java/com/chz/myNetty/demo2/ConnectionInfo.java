package com.chz.myNetty.demo2;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class ConnectionInfo
{
    private StringBuffer readBuffer = new StringBuffer();
    private AtomicInteger readBufferIndex = new AtomicInteger(0);
    private AtomicInteger blockCounter = new AtomicInteger(0);
}
