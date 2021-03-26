
#include <jni.h>
#include <string>
#include <android/log.h>    // debugging

#define  LOG_TAG    "PlotClass"
#define  ALOG(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

#ifndef STUDIENPROJEKT_ANDROID_PlotClass_H
#define STUDIENPROJEKT_ANDROID_PlotClass_H

using namespace std;

class PlotClass {
public:
    PlotClass();
    void plot(int y, double xMove, double yMove, double zoom, int itr);
    void format(int x, int y, int tmp);

    void setStop(bool stop);
    void setInterrupt(bool interrupt);
    void setSize(int width, int height);

    void resetPackage();

    string getPackageComplete();


private:
    int WIDTH{}, HEIGHT{};
    double zx{}, zy{}, cx{}, cy{}, temp{};
    string packageComplete;
    bool STOP = false;
    bool INTERRUPT = false;

};


#endif //STUDIENPROJEKT_ANDROID_PlotClass_H
