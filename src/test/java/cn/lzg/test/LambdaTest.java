package cn.lzg.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auhtor lzg
 * @Date 2016/8/2 20:55
 */
public class LambdaTest {

    @Test
    public void testStream() {
        Artist artist1 = new Artist("歌手1", "歌手1", "北京");
        Artist artist2 = new Artist("歌手2", "歌手1", "上海");
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        artistList.add(artist2);

        long count = artistList.stream().filter(artist ->{
            System.out.println(artist.getName());
            return "北京".equals(artist.getOrigin());
        }).count();
        System.out.print(count);

        List<String> result = Arrays.asList("a", "b").stream().map(str -> str.toUpperCase()).collect(Collectors.toList());
        System.out.println(result);

        Comparator

    }
}

class Artist {
    private String name;
    private String members;
    private String origin;

    public Artist(String name, String members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}

class Track {
    private String name;

    public Track(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Album {
    private String name;
    private List<Track> tracks;
    private List<Artist> musicians;

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        this.name = name;
        this.tracks = tracks;
        this.musicians = musicians;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Artist> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Artist> musicians) {
        this.musicians = musicians;
    }
}
