import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeAreaGestoreComponent } from './home-area-gestore.component';

describe('HomeAreaGestoreComponent', () => {
  let component: HomeAreaGestoreComponent;
  let fixture: ComponentFixture<HomeAreaGestoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeAreaGestoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeAreaGestoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
