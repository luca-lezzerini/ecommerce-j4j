import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeRiservataComponent } from './home-riservata.component';

describe('HomeRiservataComponent', () => {
  let component: HomeRiservataComponent;
  let fixture: ComponentFixture<HomeRiservataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeRiservataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeRiservataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
