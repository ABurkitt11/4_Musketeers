	public void buildDynamicTable(ArrayList<Item> datastorage) {
        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                int t = DTable[w][i - 1];
                if (datastorage.get(i - 1).getWeight() <= w) {
                    t = Math.max(t, datastorage.get(i - 1).getPrice() + DTable[w - datastorage.get(i - 1).getWeight()][i - 1]);
                }
                DTable[w][i] = t;
            }
        }
    }
