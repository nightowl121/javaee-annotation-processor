package com.iconloop.score.example;

import score.annotation.External;

public interface NameGetter {
    @External(readonly = true)
    String name();
}
