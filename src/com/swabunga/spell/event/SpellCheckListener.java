package com.swabunga.spell.event;

import java.util.*;

public interface SpellCheckListener extends EventListener {
	public void spellingError(SpellCheckEvent event);
}
