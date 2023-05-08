import { Component, OnInit } from '@angular/core';
import { ImageSliderInterface } from '../image-slider/image-slider.interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  slides: ImageSliderInterface[] = [
    { url: "../assets/construcao1.jpg", title: "construcao1" },
    { url: "../assets/construcao2.jpg", title: "construcao2" },
    { url: "../assets/construcao3.jpg", title: "construcao3" },
    { url: "../assets/construcao4.jpg", title: "construcao4" },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
