package net.jorjai.bummer4.business_logic;

import net.jorjai.bummer4.domain.Image;
import net.jorjai.bummer4.domain.Tag;

import java.util.List;

public interface BlInterface {
    List<Tag> getVersatileTags();

    List<Tag> getNsfwTags();

    List<Image> search(Tag tag);
}
