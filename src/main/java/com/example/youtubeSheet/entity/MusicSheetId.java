package com.example.youtubeSheet.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
public class MusicSheetId implements Serializable {

    private Long id;
    private Long siteUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicSheetId that = (MusicSheetId) o;
        return Objects.equals(id, that.id) && Objects.equals(siteUser, that.siteUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteUser);
    }
}
