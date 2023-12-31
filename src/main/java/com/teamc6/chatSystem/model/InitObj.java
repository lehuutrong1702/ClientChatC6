package com.teamc6.chatSystem.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public record InitObj(LocalDateTime creationDateTime, long userId, String userName) implements Serializable { }