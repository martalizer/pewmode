package pewmode;

 interface Spritable {
     float getxPos();

     void setxPos(float xPos);

     float getyPos();

     void setyPos(float yPos);

     float getxSpeed();

     void setxSpeed(float xSpeed);

     float getySpeed();

     void setySpeed(float ySpeed);

     void setSpeed(float speed);

     float getSpeed();

     void update();

     void render();

     void init();

     void move(float x, float y);

     void blindMove(float x, float y);

     void setAlpha(float i);

     void setScale(float v);

 }
