import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReavaliarComponent } from './reavaliar.component';

describe('ReavaliarComponent', () => {
  let component: ReavaliarComponent;
  let fixture: ComponentFixture<ReavaliarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReavaliarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReavaliarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
