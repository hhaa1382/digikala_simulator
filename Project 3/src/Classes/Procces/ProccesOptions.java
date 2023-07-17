package Classes.Procces;

import java.util.ArrayList;
import java.util.Collections;

import Classes.Products.*;

public abstract class ProccesOptions {
    static void sortProducts(ArrayList <Goods> sort,ArrayList <Goods> goods){
        ProductName type=ProductName.Mobile;
        ArrayList <Goods> temp=new ArrayList();
        while(true){
            if(type==ProductName.Mobile){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof Mobile){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                type=ProductName.Laptop;
            }

            else if(type==ProductName.Laptop){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof Laptop){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                type=ProductName.Clothes;
            }

            else if(type==ProductName.Clothes){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof Clothes){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                type=ProductName.Shoes;
            }

            else if(type==ProductName.Shoes){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof Shoes){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                type=ProductName.Television;
            }

            else if(type==ProductName.Television){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof Television){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                type=ProductName.Refrigerator;
            }

            else if(type==ProductName.Refrigerator){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof Refrigerator){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                type=ProductName.GasStove;
            }

            else if(type==ProductName.GasStove){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof GasStove){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                type=ProductName.Food;
            }

            else if(type==ProductName.Food){
                for(int i=0;i<goods.size();i++){
                    if(goods.get(i) instanceof Food){
                        temp.add(goods.get(i));
                    }
                }
                Collections.sort(temp);

                for(Goods i:temp){
                    sort.add(i);
                }
                temp.clear();
                break;
            }
        }
    }
}

enum ProductName{
    Mobile,Laptop,Clothes,Shoes,Television,Refrigerator,GasStove,Food;
}
