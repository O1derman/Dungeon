package olderman.dungeon.enemies;

public enum Enemy {
	SKELETON("a", "Skeleton"), ZOMBIE("a", "Zombie"), WARRIOR("a", "Warrior"), ASSASIN("an", "Assasin");

	public final String name;
	public final String article;

	Enemy(String article, String name) {
		this.name = name;
		this.article = article;
	}

	public String nameWithArticle() {
		return article + " " + name;
	}

}
