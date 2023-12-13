package kr.starly.core.builder;

import com.google.common.base.Preconditions;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;
import java.util.stream.Collectors;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(ItemStack item) {
        this.itemStack = item;
        this.itemMeta = item.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        Preconditions.checkNotNull(name, "The name cannot be null.");
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public String getName() {
        return itemMeta.getDisplayName();
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder setLore(List<String> lore) {
        List<String> result = lore.stream()
                .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                .collect(Collectors.toList());
        itemMeta.setLore(result);
        return this;
    }

    public ItemBuilder addLore(String... lore) {
        List<String> result = new ArrayList<>(getLore());
        result.addAll(Arrays.asList(lore));
        setLore(result);
        return this;
    }

    public List<String> getLore() {
        return itemMeta.hasLore() ? Collections.emptyList() : itemMeta.getLore();
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public boolean isUnbreakable() {
        return itemMeta.isUnbreakable();
    }

    public ItemBuilder addEnchant(Enchantment enchant, int level) {
        itemMeta.addEnchant(enchant, level, true);
        return this;
    }

    public boolean hasEnchant(Enchantment enchant) {
        return itemMeta.hasEnchant(enchant);
    }

    public Map<Enchantment, Integer> getEnchants() {
        return itemMeta.getEnchants();
    }

    public ItemBuilder addFlags(ItemFlag... flags) {
        itemMeta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder removeFlags(ItemFlag... flags) {
        itemMeta.removeItemFlags(flags);
        return this;
    }

    public ItemBuilder setSkullOwner(UUID owner) {
        Preconditions.checkNotNull(owner, "Owner UUID cannot be null.");
        Preconditions.checkArgument(itemMeta instanceof SkullMeta, "ItemMeta must be an instance of SkullMeta.");

        OfflinePlayer player = Bukkit.getOfflinePlayer(owner);
        ((SkullMeta) itemMeta).setOwningPlayer(player);
        return this;
    }

    public ItemBuilder setLeatherColor(Color color) {
        Preconditions.checkArgument(itemMeta instanceof LeatherArmorMeta, "ItemMeta must be an instance of SkullMeta.");

        ((LeatherArmorMeta) itemMeta).setColor(color);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    // Deprecated [사용 자제]
    @Deprecated
    public ItemBuilder hideAttributes() {
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }

    @Deprecated
    public ItemBuilder setOwner(UUID owner) {
        Preconditions.checkNotNull(owner, "Owner UUID cannot be null.");
        Preconditions.checkArgument(itemMeta instanceof SkullMeta, "ItemMeta must be an instance of SkullMeta.");
        OfflinePlayer player = Bukkit.getOfflinePlayer(owner);
        ((SkullMeta) itemMeta).setOwningPlayer(player);
        return this;
    }

    @Deprecated
    public ItemBuilder setColor(Color color) {
        if (itemMeta instanceof LeatherArmorMeta) ((LeatherArmorMeta) itemMeta).setColor(color);
        return this;
    }
}