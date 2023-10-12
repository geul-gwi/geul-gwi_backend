package com.posmosalimos.geulgwi;


import com.posmosalimos.geulgwi.domain.tag.constant.TagType;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @Test
    public void test() {
        System.out.println("Enum: " + TagType.DEFAULT);
        System.out.println("toString(): " + TagType.DEFAULT.toString());


        if ("DEFAULT".equals(TagType.DEFAULT.toString()))
            System.out.println(true);
        else System.out.println(false);
    }

}
