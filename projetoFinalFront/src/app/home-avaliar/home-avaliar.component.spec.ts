import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeAvaliarComponent } from './home-avaliar.component';

describe('HomeAvaliarComponent', () => {
  let component: HomeAvaliarComponent;
  let fixture: ComponentFixture<HomeAvaliarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeAvaliarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeAvaliarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
